/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.order.common.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Result holder.
 *
 * @author zhangsen
 */
public class ResultHolder {

    private static Map<String, String> orderResults = new ConcurrentHashMap<String, String>();

    private static Map<String, String> invResults = new ConcurrentHashMap<String, String>();

    private static Map<String, String> balanceResults = new ConcurrentHashMap<String, String>();

    /**
     * Order
     **/
    public static void setOrderResults(String txId, String result) {
        orderResults.put(txId, result);
    }


    public static String getOrderResults(String txId) {
        return orderResults.get(txId);
    }

    /***Inv**/

    public static void setInvResults(String txId, String result) {
        invResults.put(txId, result);
    }


    public static String getInvResults(String txId) {
        return invResults.get(txId);
    }

    /***Balance**/
    public static void setBalanceResults(String txId, String result) {
        balanceResults.put(txId, result);
    }


    public static String getBalanceResults(String txId) {
        return balanceResults.get(txId);
    }

}
