/**
 * Copyright (C) 2008 Happy Fish / YuQing
 *
 * FastDHT Java Client may be copied only under the terms of the GNU Lesser
 * General Public License (LGPL).
 * Please visit the FastDHT Home Page http://fastdht.csource.org/ for more detail.
 **/

package org.campus.csource.fastdht;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.campus.config.SystemConfig;

/**
 * Global variables
 * 
 * @author Happy Fish / YuQing
 * @version Version 1.00
 */
public class ClientGlobal {
        public static int networkTimeout; // millisecond
        public static String charset; // String charset
        public static ServerGroup serverGroup; // group info
        public static final int DEFAULT_NETWORK_TIMEOUT = 30; // second

        /**
         * load global variables
         */
        public static void init() throws FileNotFoundException, IOException, Exception {

                networkTimeout = SystemConfig.getInt("fdht.network_timeout", DEFAULT_NETWORK_TIMEOUT);
                if (networkTimeout < 0) {
                        networkTimeout = DEFAULT_NETWORK_TIMEOUT;
                }
                networkTimeout *= 1000; // millisecond

                charset = SystemConfig.getString("fdht.charset");
                if (charset == null || charset.length() == 0) {
                        charset = "UTF-8";
                }

                serverGroup = ServerGroup.loadFromFile();
        }

        private ClientGlobal() {
        }

        public static int getNetworkTimeout() {
                return networkTimeout;
        }

        public static void setNetworkTimeout(int networkTimeout) {
                ClientGlobal.networkTimeout = networkTimeout;
        }

        public static ServerGroup getServerGroup() {
                return serverGroup;
        }

        public static void setServerGroup(ServerGroup serverGroup) {
                ClientGlobal.serverGroup = serverGroup;
        }

        public static int getDefaultNetworkTimeout() {
                return DEFAULT_NETWORK_TIMEOUT;
        }
}
