package com.cyl.example.base;


import com.cyl.example.base.exception.LockingFailureException;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
public abstract class LockUtil {
    public static ConcurrentHashMap<Long, Long> lockedOrders = new ConcurrentHashMap<>();


    public static <T> T doInLock(Supplier<T> supplier, Long orderId, Long userId) {
        if (supplier == null) {
            return null;
        } else {
            //默认该用户加锁失败
            boolean locked;
            try {
                //若已有锁，则加锁失败
                if (lockedOrders.containsKey(orderId)) {
                    locked = false;
                } else {
                    ///尝试加锁
                    Long absentUser = lockedOrders.putIfAbsent(orderId, userId);
                    //若返回数据为null说明是新插入数据；
                    // 若返回数据==userId,则代表键已存在，不执行插入，直接返回旧数值
                    if (absentUser == null || absentUser.equals(userId)) {
                        locked = true;
                    } else {
                        locked = false;
                    }
                }

            } catch (Exception e) {
                throw new LockingFailureException("获取锁失败");
            }

            if (locked) {
                T result;
                try {
                    //执行业务代表
                    result = supplier.get();
                } finally {
                    //释放锁
                    unlock(orderId, userId);
                }
                return result;
            } else {
                throw new LockingFailureException("获取锁失败");
            }
        }
    }

    /**
     * 尝试加锁
     * @param orderId
     * @param userId
     * @return
     */
    public static boolean tryLock(Long orderId, Long userId) {
        if (lockedOrders.containsKey(orderId)) {
            return false;
        }
        ///尝试加锁
        Long absentUser = lockedOrders.putIfAbsent(orderId, userId);
        //若返回数据为null说明是新插入数据；
        // 若返回数据==userId,则代表键已存在，不执行插入，直接返回旧数值
        if (absentUser == null || absentUser.equals(userId)) {
            return true;
        }
        return false;
    }

    /**
     * 解锁
     *
     * @param orderId
     * @param userId
     */
    public static void unlock(Long orderId, Long userId) {
        if (userId.equals(lockedOrders.get(orderId))) {
            lockedOrders.remove(orderId);
        }
    }
}
