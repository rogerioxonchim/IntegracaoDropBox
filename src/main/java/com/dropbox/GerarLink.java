/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dropbox;

import com.dropbox.config.ConfiguracaoDropBox;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.sharing.CreateSharedLinkWithSettingsErrorException;
import com.dropbox.core.v2.sharing.PathLinkMetadata;

/**
 *
 * @author Rogerio Xonchim Alves Correa
 */
public class GerarLink {

    private ConfiguracaoDropBox config;
    
    public void gerarLink(String numCupom) {
        try {
            config = new ConfiguracaoDropBox();
            DbxClientV2 client = config.configuracao();
            PathLinkMetadata sharedLinkMetadata = client.sharing().createSharedLink("/Cupom/"+numCupom+".jpeg");
            System.out.println("Link: " + sharedLinkMetadata.getUrl());
        } catch (CreateSharedLinkWithSettingsErrorException ex) {
            System.out.println(ex);
        } catch (DbxException ex) {
            System.out.println(ex);
        }
    }
    public static void main(String[] args) {
        new GerarLink().gerarLink("1234");
    }
}
