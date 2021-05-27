/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dropbox;

import com.dropbox.config.ConfiguracaoDropBox;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author Rogerio Xonchim Alves Correa
 */
public class Upload {

    private ConfiguracaoDropBox config;

    public void upload() {
        try {
            config = new ConfiguracaoDropBox();
            DbxClientV2 client = config.configuracao();
            try (InputStream in = new FileInputStream("C:\\Users\\Rogerio\\Pictures\\Rogerio Voti.jpeg")) {
                FileMetadata metadata = client.files().uploadBuilder("/Cupom/1234.jpeg").uploadAndFinish(in);
                System.out.println(metadata);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Upload().upload();
    }

}
