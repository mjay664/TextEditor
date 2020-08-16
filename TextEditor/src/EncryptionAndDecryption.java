/*
 * Copyright (C) 2017 Jay Raj Singh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 * version 1.0 17 Jan 2017 @author Jay Raj Singh.
 * This class is used to encrypt and decrypt the given text.
 * 
 */
public class EncryptionAndDecryption {
    
    public static void main(String[] args)
    {
        EncryptionAndDecryption obj2=new EncryptionAndDecryption(123);
        obj2.generatekeys();
        String str2="Hello World";
        System.out.print(obj2.encryption(str2));
    }
    
    private final int key;
    private int[] key_set;
    private static final String ENCRYPTION_HEADER="#$*HEADER::Encryption%%%";
    private static final String ENCRYPTION_FOOTER="~!^FOOTER::Encryption@@@";
    
    public EncryptionAndDecryption(int key)
    {
        this.key=key;
        generatekeys();        
    }

    private void generatekeys() {
        KeyGenerator obj1=new KeyGenerator(key); 
        key_set=obj1.getKeySet();
    }
    
    public String encryption(String str)
    {
        if(key==0)
        {
            return "#$*Error:::Key is not valid";
        }
        
        str=encryptText(str);
        
        return str;        
    }

    private String encryptText(String str) 
    {
                String encrypted=EncryptionAndDecryption.ENCRYPTION_HEADER;
                char[] ecn=str.toCharArray();
                int i=0,j=0;
                while(i<ecn.length)
                {
                    ecn[i]=(char)((int)ecn[i]^key_set[j]);
                    i++;
                }
                i=0;
                j++;
                while(i<ecn.length)
                {
                    ecn[i]=(char)((int)ecn[i]^key_set[j]);
                    i++;
                }
                i=0;
                j++;
                while(i<ecn.length)
                {
                    ecn[i]=(char)((int)ecn[i]^key_set[j]);
                    i++;
                }
                i=0;
                j++;
                while(i<ecn.length)
                {
                    ecn[i]=(char)((int)ecn[i]^key_set[j]);
                    i++;
                }
                str=String.copyValueOf(ecn);                
                str=str+EncryptionAndDecryption.ENCRYPTION_FOOTER;
                encrypted=encrypted+str;
                return encrypted;
    }

    private String decrypt(String str) 
    {
        int header_length,footer_length;
        if(str.contains(EncryptionAndDecryption.ENCRYPTION_FOOTER))
        {
           footer_length=EncryptionAndDecryption.ENCRYPTION_FOOTER.length();
           str=str.substring(0,str.length()-footer_length);
        }
        if(str.contains(EncryptionAndDecryption.ENCRYPTION_HEADER))
            header_length=EncryptionAndDecryption.ENCRYPTION_HEADER.length();
        
        char[] ecn=str.toCharArray();
                int i=0,j=3;
                while(i<ecn.length)
                {
                    ecn[i]=(char)((int)ecn[i]^key_set[j]);
                    i++;
                }
                i=0;
                j--;
                while(i<ecn.length)
                {
                    ecn[i]=(char)((int)ecn[i]^key_set[j]);
                    i++;
                }
                i=0;
                j--;
                while(i<ecn.length)
                {
                    ecn[i]=(char)((int)ecn[i]^key_set[j]);
                    i++;
                }
                i=0;
                j--;
                while(i<ecn.length)
                {
                    ecn[i]=(char)((int)ecn[i]^key_set[j]);
                    i++;
                }
                return String.copyValueOf(ecn);
    }
    
}
