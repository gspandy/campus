package org.campus.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.campus.BaseTest;
import org.campus.model.NickName;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class NickNameServiceTest extends BaseTest {

    @Autowired
    private NickNameService nickNameService;

    @Test
    public void testInsert() {
        readTxtFile(System.getProperty("user.dir").replace("\\", "/") + "/doc/nickname.txt");
    }

    @Test
    public void testFindRandomNickName() {
        NickName name = nickNameService.findRandomNickName();
        System.out.println(name.getNickname());
    }

    private void readTxtFile(String filePath) {
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] split = lineTxt.split(",");
                    NickName record = null;
                    int i = 1;
                    nickNameService.deleteAll();
                    for (String s : split) {
                        record = new NickName();
                        record.setUid(i);
                        record.setNickname(s);
                        nickNameService.insert(record);
                        i++;
                    }
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }

}
