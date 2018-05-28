package rpc.service;

import org.apache.hadoop.ipc.ProtocolSignature;
import org.apache.hadoop.ipc.VersionedProtocol;

import java.io.IOException;

public interface ClientProtocol extends VersionedProtocol {

    public static final long versionID = 1L;

    String echo(String message) throws IOException ;

    int add(int v1, int v2) throws IOException ;
}
