/*
 * Copyright (C) 2012  Krawler Information Systems Pvt Ltd
 * All rights reserved.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

package com.krawler.spring.accounting.payment;

import com.krawler.common.service.ServiceException;
import com.krawler.spring.auditTrailModule.auditTrailDAO;
import com.krawler.utils.json.base.JSONArray;
import com.krawler.utils.json.base.JSONException;
import com.krawler.utils.json.base.JSONObject;
import com.mchange.util.AssertException;
import org.hibernate.SessionFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sagar
 */
public class accPaymentControllerTest extends AbstractTransactionalDataSourceSpringContextTests {
    private SessionFactory sessionFactory = null;
    private accPaymentDAO accPaymentDAOObj;
    private auditTrailDAO auditTrailObj;
    //company id for demo
    private static String companyid = "a4792363-b0e1-4b67-992b-2851234d5ea6";
    private static String accountId = "c340667e24dc5b090124dd8acd31002c"; // Change this id from the DB
    private static String paymentId_update = "c340667e23f149ca0124041e2e3400a4"; // Change this id from the DB
    private static String paymentId_delete = "c340667e23f149ca0124041e2e3400a4"; // Change this id from the DB
    private static String sessionParams = "{\"tzdiff\":\"-07:00\",\"timezoneid\":\"23\",\"roleid\":\"ff8080812b99b47f012b99ba5e8d0003\"," +
            "\"callwith\":1,\"companyPreferences\":\"\",\"dateformatid\":\"1\",\"lid\":\"99f1eb77-cac9-41bb-977b-c8bc17fb3daa\",\"companyid\":\"a4792363-b0e1-4b67-992b-2851234d5ea6\"," +
            "\"username\":\"demo\",\"currencyid\":\"1\",\"company\":\"Demo\",\"success\":true,\"superuser\":\"ff8080812b99b47f012b99ba5e8d0003\",\"timeformat\":2,\"userfullname\":\"Deskera \"," +
            "\"perms\":[{\"creditterm\":3},{\"paymentmethod\":3},{\"customer\":255},{\"vendor\":255},{\"coa\":4095},{\"invoice\":4294967295},{\"fstatement\":262143},{\"audittrail\":1},{\"useradmin\":7},{\"accpref\":3},{\"groups\":3},{\"product\":4095},{\"uom\":3},{\"bankreconciliation\":1},{\"currencyexchange\":3},{\"salesbyitem\":1},{\"vendorinvoice\":4294967295},{\"qanalysis\":3},{\"fixedasset\":1023},{\"masterconfig\":15},{\"importlog\":1},{\"tax\":3}]}";

    public accPaymentControllerTest(String testName) {
        super(testName);
    }

    public MockHttpSession createUserMockSession(JSONObject jObj) throws ServiceException {
        MockHttpSession session = new MockHttpSession();
        try {
            session.setAttribute("username", jObj.getString("username"));
        	session.setAttribute("userid", jObj.getString("lid"));
        	session.setAttribute("companyid", jObj.getString("companyid"));
        	session.setAttribute("company", jObj.getString("company"));
        	session.setAttribute("timezoneid", jObj.getString("timezoneid"));
        	session.setAttribute("tzdiff", jObj.getString("tzdiff"));
        	session.setAttribute("dateformatid", jObj.getString("dateformatid"));
        	session.setAttribute("currencyid", jObj.getString("currencyid"));
        	session.setAttribute("callwith", jObj.getString("callwith"));
            session.setAttribute("timeformat", jObj.getString("timeformat"));
            session.setAttribute("companyPreferences", jObj.getString("companyPreferences"));
            session.setAttribute("roleid", jObj.getString("roleid"));
        	session.setAttribute("initialized", "true");
        	session.setAttribute("userfullname", jObj.getString("userfullname"));
			JSONArray jarr = jObj.getJSONArray("perms");
        	for (int l = 0; l < jarr.length(); l++) {
				String keyName = jarr.getJSONObject(l).names().get(0)
						.toString();
				session.setAttribute(keyName, jarr.getJSONObject(l)
						.get(keyName));
			}
            return session;
        } catch (JSONException e) {
            throw ServiceException.FAILURE("sessionHandlerImpl.createUserMockSession", e);
        }
    }

    /*
     *** spring version 2.5.6 and above when extending from AbstractJUnit38SpringContextTests
     *
     *
    protected AbstractXmlApplicationContext createApplicationContext() {
    return new ClassPathXmlApplicationContext("test-applicationContext.xml");
    }*/
    protected String[] getConfigLocations() {
        return new String[]{"test-applicationContext.xml"};
    }

    public void setaccPaymentDAO(accPaymentDAO accPaymentDAOObj) {
        this.accPaymentDAOObj = accPaymentDAOObj;
    }

    public void setauditTrailDAO(auditTrailDAO auditTrailDAOObj) {
        this.auditTrailObj = auditTrailDAOObj;
    }

    /**
     * Spring will automatically inject the Hibernate session factory on startup
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Test of savePaymentMethod method, of class accPaymentController.
     */
    public void testSavePaymentMethod_add() {
        System.out.println("savePaymentMethod");
        try {
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpSession session = createUserMockSession(new JSONObject(sessionParams));
            String data = "[{methodid:\"\",methodname:\"Test-data\",accountid:\""+accountId+"\",detailtype:0,modified:true}]";
            String deleteddata = "[]";
            request.setParameter("data", data);
            request.setParameter("deleteddata", deleteddata);

            request.setSession(session);
            MockHttpServletResponse response = new MockHttpServletResponse();
            accPaymentController instance = new accPaymentController();
            instance.setaccPaymentDAO(accPaymentDAOObj);
            instance.setauditTrailDAO(auditTrailObj);
            instance.setSuccessView("jsonView");
            instance.setTxnManager((HibernateTransactionManager) transactionManager);
            ModelAndView result = instance.savePaymentMethod(request, response);
            JSONObject job = new JSONObject(result.getModel().get("model").toString());
            //commit the transaction to verify result in database.
//            transactionManager.commit(transactionStatus);
            assertTrue(job.getBoolean("success"));
        } catch (ServiceException ex) {
            throw new AssertException("Service Exception - error creating user session");
        } catch (JSONException ex) {
            throw new AssertException("JSON Exception - error parsing model json");
        }
    }

    public void testSavePaymentMethod_edit() {
        System.out.println("savePaymentMethod");
        try {
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpSession session = createUserMockSession(new JSONObject(sessionParams));
            String data = "[{methodid:\""+paymentId_update+"\",methodname:\"HDFC%20Bank1\",accountid:\""+accountId+"\",detailtype:2,modified:true}]";
            String deleteddata = "[]";
            request.setParameter("data", data);
            request.setParameter("deleteddata", deleteddata);

            request.setSession(session);
            MockHttpServletResponse response = new MockHttpServletResponse();
            accPaymentController instance = new accPaymentController();
            instance.setaccPaymentDAO(accPaymentDAOObj);
            instance.setauditTrailDAO(auditTrailObj);
            instance.setSuccessView("jsonView");
            instance.setTxnManager((HibernateTransactionManager) transactionManager);
            ModelAndView result = instance.savePaymentMethod(request, response);
            JSONObject job = new JSONObject(result.getModel().get("model").toString());
            //commit the transaction to verify result in database.
//            transactionManager.commit(transactionStatus);
            assertTrue(job.getBoolean("success"));
        } catch (ServiceException ex) {
            throw new AssertException("Service Exception - error creating user session");
        } catch (JSONException ex) {
            throw new AssertException("JSON Exception - error parsing model json");
        }
    }

    public void testSavePaymentMethod_delete() {
        System.out.println("savePaymentMethod");
        try {
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpSession session = createUserMockSession(new JSONObject(sessionParams));
            String data = "[]";
            String deleteddata = "[{methodid:'"+paymentId_delete+"',methodname:'Cash in hand',accountid:'"+accountId+"',detailtype:0,modified:false}]";
            request.setParameter("data", data);
            request.setParameter("deleteddata", deleteddata);

            request.setSession(session);
            MockHttpServletResponse response = new MockHttpServletResponse();
            accPaymentController instance = new accPaymentController();
            instance.setaccPaymentDAO(accPaymentDAOObj);
            instance.setauditTrailDAO(auditTrailObj);
            instance.setSuccessView("jsonView");
            instance.setTxnManager((HibernateTransactionManager) transactionManager);
            ModelAndView result = instance.savePaymentMethod(request, response);
            JSONObject job = new JSONObject(result.getModel().get("model").toString());
            //commit the transaction to verify result in database.
//            transactionManager.commit(transactionStatus);
            if(!job.getBoolean("success")) {
                assertEquals("Selected record(s) is currently used in the transaction(s). So it cannot be deleted.", job.getString("msg"));
            } else {
                assertTrue(job.getBoolean("success"));
            }
        } catch (ServiceException ex) {
            throw new AssertException("Service Exception - error creating user session");
        } catch (JSONException ex) {
            throw new AssertException("JSON Exception - error parsing model json");
        }
    }

    public void testSavePaymentMethod_all() {
        System.out.println("savePaymentMethod");
        try {
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpSession session = createUserMockSession(new JSONObject(sessionParams));
            String data = "[{methodid:\""+paymentId_update+"\",methodname:\"HDFC%20Bank1\",accountid:\""+accountId+"\",detailtype:2,modified:true},{methodid:\"\",methodname:\"Test-data\",accountid:\""+accountId+"\",detailtype:0,modified:true}]";
            String deleteddata = "[{methodid:'"+paymentId_delete+"',methodname:'Cash in hand',accountid:'"+accountId+"',detailtype:0,modified:false}]";
            request.setParameter("data", data);
            request.setParameter("deleteddata", deleteddata);

            request.setSession(session);
            MockHttpServletResponse response = new MockHttpServletResponse();
            accPaymentController instance = new accPaymentController();
            instance.setaccPaymentDAO(accPaymentDAOObj);
            instance.setauditTrailDAO(auditTrailObj);
            instance.setSuccessView("jsonView");
            instance.setTxnManager((HibernateTransactionManager) transactionManager);
            ModelAndView result = instance.savePaymentMethod(request, response);
            JSONObject job = new JSONObject(result.getModel().get("model").toString());
            //commit the transaction to verify result in database.
//            transactionManager.commit(transactionStatus);
            if(!job.getBoolean("success")) {
                assertEquals("Selected record(s) is currently used in the transaction(s). So it cannot be deleted.", job.getString("msg"));
            } else {
                assertTrue(job.getBoolean("success"));
            }
        } catch (ServiceException ex) {
            throw new AssertException("Service Exception - error creating user session");
        } catch (JSONException ex) {
            throw new AssertException("JSON Exception - error parsing model json");
        }
    }

    /**
     * Test of getPaymentMethods method, of class accPaymentController.
     */
    public void testGetPaymentMethods() {
        System.out.println("getPaymentMethods");
        try {
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpSession session = createUserMockSession(new JSONObject(sessionParams));
            request.setSession(session);
            MockHttpServletResponse response = new MockHttpServletResponse();
            accPaymentController instance = new accPaymentController();
            instance.setaccPaymentDAO(accPaymentDAOObj);
            instance.setauditTrailDAO(auditTrailObj);
            instance.setSuccessView("jsonView");
            ModelAndView result = instance.getPaymentMethods(request, response);
            JSONObject job = new JSONObject(result.getModel().get("model").toString());
            assertTrue(job.getBoolean("success"));
        } catch (ServiceException ex) {
            throw new AssertException("Service Exception - error creating user session");
        } catch (JSONException ex) {
            throw new AssertException("JSON Exception - error parsing model json");
        }
    }
}
