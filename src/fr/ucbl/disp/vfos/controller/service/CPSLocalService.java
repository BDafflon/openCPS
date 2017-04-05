package fr.ucbl.disp.vfos.controller.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface CPSLocalService {

	@WebMethod String getCPSStatutJSON(String name);
	@WebMethod String setCPSStatutJSON(String name);
	
	@WebMethod String getLastDecision(String name);
	@WebMethod String get5LastDecision(String name);
	
	@WebMethod String getPredictDecision(String name);


}
