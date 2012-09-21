package com.df12.tools;

/**
 * Copyright (c) 2012, FinancialForce.com, inc
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * - Neither the name of the FinancialForce.com, inc nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 * OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
**/

import java.rmi.RemoteException;

//import javax.xml.rpc.ServiceException;


import com.sforce.soap._2006._08.apex.SessionHeader;
import com.sforce.soap._2006._08.apex.ApexPortType;
import com.sforce.soap._2006._08.apex.ApexServiceLocator;
import com.sforce.soap._2006._08.apex.DebuggingHeader;
import com.sforce.soap._2006._08.apex.ExecuteAnonymousResult;
import com.sforce.soap._2006._08.apex.LogType;

import org.apache.axis.client.Stub;
import com.sforce.soap.partner.LoginResult;
import com.sforce.soap.partner.SforceServiceLocator;
import com.sforce.soap.partner.Soap;


public class ExecuteAnonymous {

	private static LoginResult m_login;
	
	public static void main(String[] args)
	{
		executeAnonymous(args[0],args[1],args[2]);		
	}
	
	public static void executeAnonymous(String user, String password, String anonApex) 
	{
		try 
		{
			ApexPortType svc = null;
			ExecuteAnonymousResult res = null;
			try {svc = getApexService(user,password);}catch (Exception e){e.printStackTrace();}
			res = svc.executeAnonymous(anonApex);
			System.out.println("success?");
			System.out.println(res.isSuccess());
			if (!res.isSuccess())
			{
			  if (!res.isCompiled())
			    {System.out.println(res.getCompileProblem());}
			  else
			  {
				System.out.println(res.getExceptionMessage());
				System.out.println(res.getExceptionStackTrace());				  
			  }  
			}
		} 
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	
	}
	
	private static ApexPortType getApexService(String user, String password) throws Exception
	{
		ApexPortType svc;
		SessionHeader sessionHeader;
		DebuggingHeader debuggingHeader;
		ApexServiceLocator locator;
		sessionHeader = new SessionHeader();
		login(user,password);
		 
		sessionHeader.setSessionId(m_login.getSessionId());
		debuggingHeader = new DebuggingHeader();
		debuggingHeader.setDebugLevel(LogType.Profiling);
		locator = new ApexServiceLocator();
		svc = locator.getApex();
 	    ((Stub) svc).setHeader(locator.getServiceName().getNamespaceURI(), "SessionHeader", sessionHeader);
		((Stub) svc).setHeader(locator.getServiceName().getNamespaceURI(), "DebuggingHeader", debuggingHeader);
		((Stub) svc)._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, m_login.getServerUrl().replaceAll("/u/", "/s/")); 

		return svc;
	}

	private static LoginResult login(String user,String password) throws Exception
	{
		if (m_login == null)
		{
			Soap soap;
			LoginResult result;
			soap = new SforceServiceLocator().getSoap();
			result = soap.login(user,password);
			m_login = result;
		}
		return m_login;
	}

	
}
