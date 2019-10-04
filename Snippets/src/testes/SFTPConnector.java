package testes;

import com.jcraft.jsch.*;
import java.io.*;
import java.util.Vector;
import org.apache.commons.io.FileUtils;



/**
  * @author Dex
  * @since 10/04/2019
  * @version 1.0.0-20191004-35
  *
  * Changelog:
  * 
  * 
  * Class used to stabilish network connections to SFTP transfers.
  */
public class SFTPConnector
{
  /* Local file object in String form. Can be source or destination. */
  String localfile;
  /* Remote file object in String form. Can be source or destination. */
  String remotefile;
  /* SFTP Server IP */
  String serverip;
  /* SFTP Server Port */
  int serverport;
  /* SFTP Server Username */
  String serveruser;
  /* SFTP Server Password */
  String serverpass;
  /* SFTP Server Session and Channels */
  Session session;
  Channel channel;
  ChannelSftp channelsftp;
  
  
  public void setLocal(String fil) { this.localfile = fil; }

  public String getLocal() { return this.localfile; }
  
  public void setRemote(String fil) { this.remotefile = fil; }
  
  public String getRemote() { return this.remotefile; }
  
  public void setServerIP(String ip) { this.serverip = ip; }

  public String getServerIP() { return this.serverip; }

  public void setServerPort(int port) { this.serverport = port; }

  public int getServerPort() { return this.serverport; }
  
  public void setServerUser(String user) { this.serveruser = user; }

  public String getServerUser() { return this.serveruser; }
  
  public void setServerPass(String pass) { this.serverpass = pass; }

  public String getServerPass() { return this.serverpass; }
  
  
  public void connect() throws JSchException
  {
    JSch jsch = new JSch();
    session = jsch.getSession(getServerUser(), getServerIP(), getServerPort());
    session.setConfig("StrictHostKeyChecking", "no");
    session.setPassword(getServerPass());
    session.connect();
    System.out.println("Session Connected.");
    channel = session.openChannel("sftp");
    channel.connect();
    System.out.println("Channel Connected.");
    channelsftp = (ChannelSftp) channel;
    System.out.println("SFTP Channel Connected.");
  }
  
  public void disconnect()
  {
    if (channelsftp != null)
    {
      channelsftp.disconnect();
      System.out.println("SFTP Channel Disconnected.");
      channel.disconnect();
      System.out.println("Channel Disconnected.");
      session.disconnect();
      System.out.println("Session Disconnected.");
    }
    else { System.out.println("ERROR: Channel is null."); }
  }
  
  public void upload() throws SftpException, IOException, FileNotFoundException, InterruptedException, JSchException
  {
    String sourcePath = getLocal();
    String destinationPath = getRemote();
    recursiveFileUpload(sourcePath, destinationPath);
  }
  
  public void download() throws SftpException, IOException, InterruptedException, JSchException
  {
    String sourcePath = getRemote();
    String destinationPath = getLocal();
    File dest = new File (destinationPath);
    if (!dest.exists()) { if (dest.isFile()) { FileUtils.touch(dest); } else { dest.mkdir(); } }
    recursiveFileDownload(sourcePath, destinationPath);
  }

  public void recursiveFileUpload(String sourcePath, String destinationPath) throws SftpException, FileNotFoundException, IOException, InterruptedException, JSchException 
  {
    File sourceFile = new File(sourcePath);
    if (sourceFile.isFile()) 
    {
      channelsftp.cd(destinationPath);
      System.out.println("Enviando arquivo \"" + sourceFile.getName() + "\"");
      if (!sourceFile.getName().startsWith(".")) { channelsftp.put(new FileInputStream(sourceFile), sourceFile.getName(), ChannelSftp.OVERWRITE); }
    } 
    else 
    {
      File[] files = sourceFile.listFiles();
      if (files != null && !sourceFile.getName().startsWith(".")) 
      {
        channelsftp.cd(destinationPath);
        SftpATTRS attrs = null;
        try { attrs = channelsftp.stat(destinationPath + "/" + sourceFile.getName()); } catch (SftpException e) { System.out.println("\"" + destinationPath + "/" + sourceFile.getName() + "\" NÃO FOI ENCONTRADO."); }
        if (attrs != null) { System.out.println("O diretório \"" + destinationPath + "\" existe no backup remoto."); }
        else 
        {
          System.out.println("Criando Diretório remoto \"" + sourceFile.getName() + "\"");
          channelsftp.mkdir(sourceFile.getName());
        }
        for (File f: files) { recursiveFileUpload(f.getAbsolutePath(), destinationPath + "/" + sourceFile.getName()); }
      }
    }
  }
  
  @SuppressWarnings("unchecked")
  public void recursiveFileDownload(String sourcePath, String destinationPath) throws SftpException, IOException, InterruptedException, JSchException 
  {
    Vector<ChannelSftp.LsEntry> fileAndFolderList = channelsftp.ls(sourcePath);
    for (ChannelSftp.LsEntry item : fileAndFolderList)
    {
      if (!item.getAttrs().isDir())
      {
        if (!(new File(destinationPath + "/" + item.getFilename())).exists() || (item.getAttrs().getMTime() > Long.valueOf(new File(destinationPath + "/" + item.getFilename()).lastModified() / 1000).intValue()))
        { 
          System.out.println("Baixando arquivo \"" + item.getFilename() + "\"");
          channelsftp.get(sourcePath + "/" + item.getFilename(), destinationPath + "/" + item.getFilename());
        }
      }
      else if (!(".".equals(item.getFilename()) || "..".equals(item.getFilename())))
      {
        System.out.println("Criando diretório local \"" + item.getFilename() + "\"");
        new File(destinationPath + "/" + item.getFilename()).mkdirs();
        recursiveFileDownload(sourcePath + "/" + item.getFilename(), destinationPath + "/"+ item.getFilename());
      }
    }
  }
  
  /* For Testing */
//  public static void main(String[] args) throws JSchException, SftpException, IOException, FileNotFoundException, InterruptedException
//  {
//    SFTPAPI a = new SFTPAPI();
//    a.setLocal("C:\\DexCraft\\rtm");
//    a.setRemote("/rtm/dcbat");
//    a.setServerIP("192.167.1.100");
//    a.setServerPort(22);
//    a.setServerUser("user");
//    a.setServerPass("pass");
//    a.connect();
//    a.download();
//    a.disconnect();
//    System.out.println("Done.");
//  }
   
}

