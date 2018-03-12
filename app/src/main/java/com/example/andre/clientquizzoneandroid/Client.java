package com.example.andre.clientquizzoneandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

public class Client implements Serializable{
	
	private Socket s;
	private String ip;
	private int porta;
	private boolean ready = false;

	private InputStreamReader isr;
	private BufferedReader in;

	private PrintWriter out;

	public Client(String ip)
	{
		this.ip = ip;
		porta = 9999;
	}

	public Client(String ip, int porta)
	{
		this.ip = ip;
		this.porta = porta;
	}
	
	public void creaConnessione()
	{
		try {
			s= new Socket (ip, porta);

			isr = new InputStreamReader(s.getInputStream());
			in = new BufferedReader(isr);

			out = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isConnected()
	{
		if(s == null)
			return false;
		else
			return s.isConnected();
	}
	
	public boolean attendi(){

		int cont=0;
		while(!ready){
			System.out.println("Entrato");
			InputStreamReader isr = null;
			try {

				String t = new String (in.readLine());
				if(t.equals("$Iniziamo!$")){
					ready = true;
				}
				cont++;
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(ready + "  ready");
		}

		return ready;
	}

	public boolean isReady()
	{
		return ready;
	}

	public String getText(){
		String t="";
		boolean flag=false;
		while(!flag){
			try {
				System.out.println(s.isConnected());
				t = new String (in.readLine());
				if(t.indexOf("$")==0){
					flag=true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public void invio(String n)
	{
		out.println(n);
		System.out.println("Invio");
	}
	
	public void close() {
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
