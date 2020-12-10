package com.order.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.order.bean.OrderBean;
import com.order.bean.OrderForm;
import com.order.service.OrderService;

/*
 * Serviceを起動したりViewにデータを渡す注文情報クラスです
 */

@Controller
public class OrderController {
  @ModelAttribute
  OrderForm setupForm() {
    return new OrderForm();
  }

  @Autowired
  OrderService orderService;

  /*
   * 一覧表示機能です。
   */
  @RequestMapping(value = "/order")
  public String initIndex(Model model) {
    List<OrderBean> orderList = orderService.findAllOrder();
    model.addAttribute("orderList", orderList);
    model.addAttribute("orderForm", new OrderForm());
    return "order/o_index";
  }

  /*
   * 新規登録画面にcustomerFormのインスタンスと共に遷移します
   */
  @RequestMapping(value = "/order/create")
  public String create(@ModelAttribute("orderForm") OrderForm orderForm, Model model) {
    return "order/o_create";
  }

  /*
   * DBにInsertして保存完了画面に遷移します
   */
  @RequestMapping(value = "/order/save-create")
  public String saveForm(@ModelAttribute @Validated OrderForm orderForm,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return "order/o_create";
    } else {
      try {
        orderService.createOrder(orderForm);
        model.addAttribute("orderForm", orderForm);
        return "order/o_save";
      } catch (Exception e) {
        model.addAttribute("errorMessage", "注文番号が重複しています。別の番号を指定してください");
        return "order/o_create";
      }
    }
  }

  /*
   * 詳細機能です。顧客番号を受け取り詳細画面に遷移します
   */
  @RequestMapping(value = "/order/{order_no}")
  public String deleteForm(@PathVariable Integer order_no, Model model) {
    List<OrderBean> orderList = orderService.findByNumber(order_no);
    model.addAttribute("orderList", orderList);

    return "order/o_detail";
  }

  /*
   * 削除機能です。削除画面は用意せずdeleteしたら一覧画面に遷移します。
   */
  @RequestMapping(value = "/order/delete/{order_no}")
  public String deleteForm(@PathVariable Integer order_no, RedirectAttributes attributes,
      Model model) {
    orderService.deleteOrder(order_no);
    attributes.addFlashAttribute("deleteMessage", "注文番号:" + order_no + "のデータを削除しました");

    return "redirect:/order";
  }

  /*
   * 検索機能です。OrderFormのkeyとkeywordを使いDBに検索をかけます。
   */
  @RequestMapping(value = "/order/search")
  public String searchForm(OrderForm orderForm, Model model) {
    if (orderForm.getKeyword().equals("")) {
      model.addAttribute("errorMessage", "キーワードを入力してください");
      return "order/o_search";

    } else {

      List<OrderBean> searchList = new ArrayList<OrderBean>();

      searchList = orderService.searchByKeyword(orderForm);
      model.addAttribute("searchList", searchList);

      try {
        if (searchList.get(0) == null) {
        }
      } catch (Exception e) {
        model.addAttribute("emptyMessage", "検索結果は0件です");
        return "order/o_search";
      }

      return "order/o_search";
    }
  }

  /*
   * 更新画面に該当の注文情報が入ったデータを渡すメソッドです。
   */
  @RequestMapping(value = "/order/edit/{order_no}")
  public String editForm(@ModelAttribute OrderForm orderForm, @PathVariable Integer order_no,
      Model model) {
    orderForm = orderService.editOrderForm(order_no);
    model.addAttribute("orderForm", orderForm);
    System.out.println("編集前の更新時間" + orderForm.getUpdate_date());
    return "order/o_edit";
  }

  /*
   * 編集機能です。入力エラーの場合更新しません
   */
  @RequestMapping(value = "/order/save-edit")
  public String updateSave(@ModelAttribute @Validated OrderForm orderForm,
      BindingResult bindingResult, Model model) {

    System.out.println("フォームの更新時間" + orderForm.getUpdate_date());
    System.out.println("DB上の更新時間" + orderService.checkByTime(orderForm.getCust_no()));

    if (bindingResult.hasErrors()) {
      return ("order/o_edit");
    } else if (orderService.checkByTime(orderForm.getCust_no())
        .equals(orderForm.getUpdate_date())) {
      orderService.editOrder(orderForm);
      return ("order/o_save");
    } else {
      model.addAttribute("errorMessage",
          "他のユーザーが注文番号" + orderForm.getCust_no() + "の情報を編集したため、編集できません。一覧に戻り確認をお願いします");
      return ("order/o_edit");
    }
  }
}
