/**
 * Copyright (C) 2008 Happy Fish / YuQing
 *
 * FastDFS Java Client may be copied only under the terms of the GNU Lesser
 * General Public License (LGPL).
 * Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
 **/

package org.campus.csource.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ini file reader / parser
 * 
 * @version Version 1.1
 */
public class IniFileReader {
        private PropertiesLoader loader;
        private String conf_filename;

        /**
         * 
        * <p>Title: </p>
        * <p>Description: </p>
        * @param conf_filename
        * @throws FileNotFoundException
        * @throws IOException
         */
        public IniFileReader(String conf_filename) throws FileNotFoundException, IOException {
                this.conf_filename = conf_filename;
                loadFromFile(conf_filename);
        }

        /**
         * 
        * @Description: TODO(这里用一句话描述这个方法的作用)
        * @author LiuYi
        * @date 2014年6月5日 上午10:11:14
        *  @return  String
         */
        public String getConfFilename() {
                return this.conf_filename;
        }

        /**
         * 
        * @Description: TODO(这里用一句话描述这个方法的作用)
        * @author LiuYi
        * @date 2014年6月6日 上午10:11:11
        *  @param name
        *  @return  String
         */
        public String getStrValue(String name) {
                return this.loader.getProperty(name);
        }

        /**
         * 
        * @Description: TODO(这里用一句话描述这个方法的作用)
        * @author LiuYi
        * @date 2014年6月5日 上午10:11:01
        *  @param name
        *  @param default_value
        *  @return  int
         */
        public int getIntValue(String name, int default_value) {
                String szValue = this.loader.getProperty(name);
                if (szValue == null || "".equals(szValue)) {
                        return default_value;
                }
                return Integer.parseInt(szValue);
        }

        /**
         * 
        * @Description: TODO(这里用一句话描述这个方法的作用)
        * @author LiuYi
        * @date 2014年6月5日 上午10:10:53
        *  @param name
        *  @param default_value
        *  @return  boolean
         */
        public boolean getBoolValue(String name, boolean default_value) {
                String szValue = this.loader.getProperty(name);
                if (szValue == null) {
                        return default_value;
                }
                return szValue.equalsIgnoreCase("yes") || szValue.equalsIgnoreCase("on")
                                || szValue.equalsIgnoreCase("true") || szValue.equals("1");
        }

        /**
         * 
        * @Description: TODO()
        * @author LiuYi
        * @date 2014年6月5日 上午10:10:35
        *  @param name
        *  @return  String[]
         */
        public String[] getValues(String name) {
                List<String> values = new ArrayList<String>();
                String val = this.loader.getProperty(name);
                if (val.contains(",")) {
                        for (String v : val.split(",")) {
                                values.add(v);
                        }
                } else {
                        values.add(val);
                }
                return values.toArray(new String[values.size()]);
        }
        /**
         * 
        * @Description: TODO(这里用一句话描述这个方法的作用)
        * @author LiuYi
        * @date 2014年6月5日 上午10:11:54
        *  @param resourcesPaths
        *  @throws FileNotFoundException
        *  @throws IOException  void
         */
        private void loadFromFile(String... resourcesPaths) throws FileNotFoundException, IOException {
                this.loader = new PropertiesLoader(resourcesPaths);
        }
}
