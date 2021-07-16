package com.sportyshoes.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sportyshoes.model.PurchaseOrder;
import com.sportyshoes.model.SportShoes;
import com.sportyshoes.model.User;
import com.sportyshoes.service.PurchaseOrderService;

@Controller
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderService service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-purchaseOrders", method = RequestMethod.GET)
	public String showPurchaseOrder(ModelMap model) {
		model.put("purchaseOrderlist", service.retrievePurchaseOrder());
		return "list-purchaseOrders";
	}

	@RequestMapping(value = "/search-purchaseOrders", method = RequestMethod.GET)
	public String searchPurchaseOrder(ModelMap model, @Param("date") String date, @Param("shoetype") String shoetype) {
		
		if (date != null) {
			model.addAttribute("purchaseOrderlist", service.searchPurchaseOrdersByDate(date));
			model.addAttribute("date", date);
			return "search-purchaseOrders";
		}
		if (shoetype != null)
		{
			model.addAttribute("purchaseOrderlist", service.searchPurchaseOrdersByShoeType(shoetype));
			model.addAttribute("shoetype", shoetype);
			return "search-purchaseOrders";
		}
	
		model.addAttribute("purchaseOrderlist", service.retrievePurchaseOrder());
		return "search-purchaseOrders";
	}

	@RequestMapping(value = "/add-purchaseOrder", method = RequestMethod.GET)
	public String showAddPurchaseOrderPage(ModelMap model) {
		model.addAttribute("purchaseOrder", new PurchaseOrder((User) model.get("user"),
															  (List<SportShoes>) model.get("shoes"),
												  		 	  (Date) model.get("date"),
												  		 	  21.5));//Double.parseDouble((String) model.get("price"))));
    	System.out.println(model);
		return "purchaseOrder";
	}

	@RequestMapping(value = "/add-purchaseOrder", method = RequestMethod.POST)
	public String addPurchaseOrder(ModelMap model, PurchaseOrder purchaseOrder, BindingResult result) {
		
		if (result.hasErrors()) {
			return "purchaseOrder";
		}
		
		purchaseOrder = (PurchaseOrder) model.get("purchaseOrder");
		System.out.println("Model: "+model);
		
		service.addPurchaseOrder(purchaseOrder.getUser(),
				purchaseOrder.getShoes(),
				purchaseOrder.getPurchaseDate(),
				purchaseOrder.getBilledAmount());
		return "redirect:/list-purchaseOrders";
	}

//	@RequestMapping(value = "/delete-purchaseOrder", method = RequestMethod.GET)
//	public String deletePurchaseOrder(@RequestParam int id) {
//		System.out.println("deleting "+ id);
//		service.deletePurchaseOrder(id);
//		return "redirect:/list-purchaseOrders";
//	}
//
//	@RequestMapping(value = "/update-purchaseOrder", method = RequestMethod.GET)
//	public String showUpdatePurchaseOrderPage(@RequestParam int id, ModelMap model) {
//		PurchaseOrder purchaseOrder = service.retrievePurchaseOrder(id);
//		model.put("purchaseOrder", purchaseOrder);
//		return "purchaseOrder";
//	}

//	@RequestMapping(value = "/update-purchaseOrder", method = RequestMethod.POST)
//	public String updatePurchaseOrder(ModelMap model, PurchaseOrder purchaseOrder, BindingResult result) {
//
//		if (result.hasErrors()) {
//			return "purchaseOrder";
//		}
//
//		purchaseOrder = (PurchaseOrder) model.get("purchaseOrder");
//
//		purchaseOrder.getShoes().addAll((List<SportShoes>) model.getAttribute("shoes"));
//
//		service.updatePurchaseOrder(purchaseOrder);
//
//		return "redirect:/list-purchaseOrders";
//	}

//	@RequestMapping(value = "/selectdates", method = RequestMethod.POST)
//	public String selectDates(ModelMap model, @ModelAttribute("poDateFilter") DateFilter dateFilter, BindingResult result) {
//		
//		if (result.hasErrors()) {
//			return "purchaseOrder";
//		}
//		
//		dateFilter = (DateFilter) model.get("poDateFilter");
//		
//		return "redirect:/list-purchaseOrders";
//	}
//
//	@RequestMapping(value = "/selectdates", method = RequestMethod.GET)
//	public String selectDates(ModelMap model) {
//
//		model.addAttribute("poDateFilter", new DateFilter());
//		
//		return "redirect:/list-purchaseOrders";
//	}

}
