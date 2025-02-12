package com.back_end_project.back_end_project.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back_end_project.back_end_project.controllerComponentECPay.EcpayFunctions;
import com.back_end_project.back_end_project.database.Orders;
import com.back_end_project.back_end_project.service.OrdersService;

@Controller
@RequestMapping("/pages/ecpay")
@CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端域名
public class EcpayController {

	@Autowired
	private EcpayFunctions ecpayFunctions;

	@Autowired
	private OrdersService ordersService;

	/**
	 * 處理 ECPay 的返回請求
	 * 
	 * @param body 接收到的返回請求內容
	 * @return 空字串
	 */
	@PostMapping("/return")
	public String ecpayReturn(@RequestBody String body) {
		System.out.println("【ECPay Return API 被呼叫】");
		System.out.println("接收到的時間：" + System.currentTimeMillis());
		System.out.println("接收到的請求內容：" + body);

		JSONObject jsonObject = new JSONObject(body);
		String orderId = jsonObject.getString("MerchantTradeNo");
		int rtnCode = jsonObject.getInt("RtnCode");
		String rtnMsg = jsonObject.getString("RtnMsg");

		try {
			// 取得 "ID" 後的數字
			int index = orderId.indexOf("ID");
			if (index != -1) {
				orderId = orderId.substring(index + 2); // 取得 "ID" 後的數字部分
			}

			// 轉換成 Integer
			int orderIdInt = Integer.parseInt(orderId);

			if (rtnCode == 1) {
				System.out.println("✅ 訂單 " + orderIdInt + " 付款成功！");

				// ** 使用 `ordersService` 查詢訂單 **
				Orders order = ordersService.findOrderById(orderIdInt);
				if (order != null) {
					order.setPaymentStatus("Paid");

					// ** 使用 `ordersService` 更新訂單 **
					ordersService.saveOrder(order);
					System.out.println("📝 訂單 " + orderIdInt + " 狀態已更新為已付款！");
				} else {
					System.out.println("⚠️ 找不到訂單 " + orderIdInt);
				}
			} else {
				System.out.println("❌ 訂單 " + orderIdInt + " 付款失敗：" + rtnMsg);
				// **刪除該筆未付款訂單**
				ordersService.deleteOrderById(orderIdInt);
			}
		} catch (NumberFormatException e) {
			System.out.println("❌ 訂單 ID 轉換失敗：" + orderId);
		}

		return "OK";
	}

	/**
	 * 處理發送 ECPay 請求並生成支付表單
	 * 
	 * @param body 從前端接收到的請求內容
	 * @return HTML 表單字串
	 */
	@PostMapping("/send")
	@ResponseBody
	@CrossOrigin
	public String send(@RequestBody String body) {
		// 日誌：檢查接收到的請求 body
		System.out.println("【ECPay Send API 被呼叫】");
		System.out.println("接收到的時間：" + System.currentTimeMillis());
		System.out.println("接收到的請求內容：" + body);

		// 呼叫 ECPay 功能類生成支付表單
		String form = ecpayFunctions.buildEcpayForm(body);

		// 日誌：生成的表單內容
		System.out.println("生成的支付表單內容：");
		System.out.println(form);

		return form;
	}
}
