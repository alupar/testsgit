package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.apache.axis.Handler;
import org.apache.axis.SimpleChain;
import org.apache.axis.SimpleTargetedChain;
import org.apache.axis.client.AxisClient;
import org.apache.axis.configuration.SimpleProvider;
import org.apache.axis.transport.http.HTTPSender;
import org.apache.axis.transport.http.HTTPTransport;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class SoapTestsBarantsev {

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator","root");
    System.out.println(projects.length);
    for(ProjectData project: projects){
      project.getName();
    }
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    SimpleProvider clientConfig = new SimpleProvider();
    AxisLogHandler logHandler = new AxisLogHandler();
    SimpleChain reqHandler = new SimpleChain();
    SimpleChain respHandler = new SimpleChain();
    reqHandler.addHandler(logHandler);
    respHandler.addHandler(logHandler);
    Handler pivot = new HTTPSender();
    Handler transport = new SimpleTargetedChain(reqHandler, pivot, respHandler);
    clientConfig.deployTransport(HTTPTransport.DEFAULT_TRANSPORT_NAME, transport);

    MantisConnectLocator locator = new MantisConnectLocator();
    locator.setEngineConfiguration(clientConfig);
    locator.setEngine(new AxisClient(clientConfig));
    return locator.getMantisConnectPort(new URL("http://localhost/mantisbt-2.22.0/api/soap/mantisconnect.php"));
  }

}
