
package ccclient;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TBinaryProtocol;

import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.EditPlugin;
import org.gjt.sp.jedit.MiscUtilities;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.util.Log;

import org.zjg.ccs.CCS.Client;

public class CCClientPlugin extends EditPlugin
{
   public static final String NAME = "CCClient";
   public static final String OPTION = "options.CCClient.";
   public static final String MESSAGE = "messages.CCClient.";

   private static Client ccsClient_ = null;
   private static TTransport transport_ = null;

   @Override
   public void start()
   {
      try {
         transport_ = new TSocket("localhost", 9515);
         transport_.open();
         TProtocol protocol = new TBinaryProtocol(transport_);
         ccsClient_ = new Client(protocol);
      }
      catch (TTransportException e) {
         Log.log(Log.DEBUG, this, "TTransportException " + e.getType());
      }

      try {
         String versionInfo = ccsClient_.getVersionInfo();
         Log.log(Log.MESSAGE, this, "server version: " + versionInfo);
      }
      catch (TTransportException e) {
         Log.log(Log.DEBUG, this, "TTransportException " + e.getType());
      }
      catch (TException e) {
         Log.log(Log.DEBUG, this, "TException! " + e.toString());
      }
   }

   @Override
   public void stop()
   {
      ccsClient_ = null;

      transport_.close();
      transport_ = null;
   }

   static public Client getCcsClient()
   {
      return ccsClient_;
   }

}
