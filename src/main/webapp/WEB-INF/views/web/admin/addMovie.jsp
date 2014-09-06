<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 07.09.2014
  Time: 1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

    <form action="" method="post">
        <div class="row">
            <div class="form-element width-12">
                <label for="title" class="width-2">Название</label>
                <input id="title" class="form-input width-6" type="text" name="title">
            </div>
        </div>
        <div class="row">
            <div class="form-element width-12">
                <label for="localizedTitle" class="width-2">Название на русском языке (если есть)</label>
                <input id="localizedTitle" class="form-input width-6" type="text" name="localizedTitle">
            </div>
        </div>
        <div class="row">
            <div class="form-element width-12">
                <label for="coverURL" class="width-2">URL картинки</label>
                <input id="coverURL" class="form-input width-6" type="text" name="coverURL">
            </div>
        </div>
        <div class="row">
            <div class="form-element width-12">
                <label for="price" class="width-2">Цена</label>
                <input id="price" class="form-input width-6" type="text" name="price">
            </div>
        </div>
        <div class="row">
            <div class="form-element width-12">
                <label for="currency" class="width-2">Валюта</label>
                <select class="form-input width-6" id="currency" name="currency">
                    <option value="RUS_RUBLES">Рубли</option>
                    <option value="US_DOLLARS">Доллары</option>
                </select>
            </div>
        </div>
    </form>

</div>