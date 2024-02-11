package no.hvl.dat110.system.display;

import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.system.controller.Common;

public class DisplayDevice {

	public static void main(String[] args) {

		System.out.println("Display server starting ...");

		// create an RPC server for the display
		RPCServer displayServer = new RPCServer(Common.DISPLAYPORT);

		// create a display implementation object
		DisplayImpl displayImpl = new DisplayImpl((byte)(0), displayServer);

		// register the display implementation object on the display RPC server
		displayServer.register((byte)(0), displayImpl);

		// start the display RPC server
		displayServer.run();

		System.out.println("Display server stopping ...");

	}
}
