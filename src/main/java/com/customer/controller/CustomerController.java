package com.customer.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.customer.bean.CustomerBean;
import com.customer.bean.CustomerForm;
import com.customer.service.CustomerService;

/*
 * Serviceを起動したりViewにデータを渡す顧客情報コントローラークラスです
 */

@Controller
public class CustomerController {
  @ModelAttribute
  CustomerForm setUpForm() {
    return new CustomerForm();
  }

  @Autowired
  CustomerService customerService;

  @RequestMapping(value = "/customer")
  public String initIndex(Model model) {

    /*
     * 一覧表示機能
     */
    List<CustomerBean> customerList = customerService.findAllCustomer();
    model.addAttribute("customerList", customerList);
    model.addAttribute("customerForm", new CustomerForm());
    return "customer/index";
  }

  /*
   * 新規登録画面にcustomerFormのインスタンスと共に遷移します
   */
  @RequestMapping(value = "/customer/create")
  public String create(@ModelAttribute("customerForm") CustomerForm customerForm, Model model) {
    return "customer/create";
  }

  /*
   * DBにInsertして保存完了画面に遷移します
   */
  @RequestMapping(value = "/customer/save-create")
  public String saveForm(@ModelAttribute @Validated CustomerForm customerForm,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return "customer/create";
    } else {
      try {
        customerService.createCustomer(customerForm);
        model.addAttribute("customerForm", customerForm);
        return "customer/save";

      } catch (Exception e) {
        model.addAttribute("errorMessage", "顧客番号が重複しています。別の番号を指定してください");
        return "customer/create";
      }

    }
  }

  /*
   * 詳細機能です。顧客番号を受け取り詳細画面に遷移します
   */
  @RequestMapping(value = "/customer/{cust_no}", method = RequestMethod.GET)
  public ModelAndView detailForm(@PathVariable Integer cust_no) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("customer/detail");

    List<CustomerBean> customerList = customerService.findByNumber(cust_no);
    mv.addObject("customerList", customerList);

    return mv;
  }

  /*
   * 削除機能です。削除画面は用意せずにdeleteしたら一覧画面に遷移します。
   */
  @RequestMapping(value = "/customer/delete/{cust_no}")
  public String deleteForm(@PathVariable Integer cust_no, RedirectAttributes attributes,
      Model model) {
    customerService.deleteCustomer(cust_no);

    attributes.addFlashAttribute("deleteMessage", "顧客番号:" + cust_no + "を削除しました");

    return "redirect:/customer";
  }

  /*
   * 検索機能です。CustomerFormのkeyとkeywordを使いDBに検索を掛けます。
   */
  @RequestMapping(value = "/customer/search", method = RequestMethod.POST)
  public String searchForm(CustomerForm customerForm, Model model) {

    if (customerForm.getKeyword().equals("")) {
      model.addAttribute("errorMessage", "キーワードを入力してください");
      return "customer/search";
    }
    List<CustomerBean> searchList = new ArrayList<CustomerBean>();

    searchList = customerService.searchByKeyword(customerForm);
    model.addAttribute("searchList", searchList);

    try {
      if (searchList.get(0) == null) {
      }
    } catch (Exception e) {
      model.addAttribute("emptyMessage", "検索結果は0件です");
      return "customer/search";
    }

    return "customer/search";
  }

  /*
   * 更新画面に顧客情報が入ったデータを渡すメソッドです。
   */
  @RequestMapping(value = "/customer/edit/{cust_no}", method = RequestMethod.GET)
  public String editForm(@ModelAttribute CustomerForm customerForm, @PathVariable Integer cust_no,
      Model model) {

    customerForm = customerService.editCustomerForm(cust_no);
    model.addAttribute("customerForm", customerForm);

    return "customer/edit";
  }

  /*
   * 編集機能です。入力エラーの場合更新しません
   */
  @RequestMapping(value = "/customer/save-edit")
  public String updateSave(@ModelAttribute @Validated CustomerForm customerForm,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return ("customer/edit");
    } else {
      customerService.editCustomer(customerForm);
      return ("customer/save");
    }
  }

}
