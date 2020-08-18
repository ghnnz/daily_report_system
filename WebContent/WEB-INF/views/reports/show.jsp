<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
    rel="stylesheet">
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">
                <h2>日報 詳細ページ</h2>
                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${report.employee.name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${report.report_date}"
                                    pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td><pre>
                                    <c:out value="${report.content}" />
                                </pre></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td><fmt:formatDate value="${report.created_at}"
                                    pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td><fmt:formatDate value="${report.updated_at}"
                                    pattern="yyyy-MM-dd HH:mm:ss" /></td>

                        </tr>
                    </tbody>
                </table>



                <c:choose>
                    <c:when
                        test="${sessionScope.login_employee.id == report.employee.id}">
                        <p>
                            <a href="<c:url value="/reports/edit?id=${report.id}" />">この日報を編集する</a>
                        </p>
                    </c:when>


                    <c:otherwise>
                        <c:choose>

                        <c:when test="${getReportId > 0}" >
                            <form method="post"
                                action="<c:url value='/likes/destroy' />">
                                <button type="submit"><i class="fas fa-heart"></i> いいねを取り消す</button>
                                <input type=hidden name="report_id" value="${report.id}" />
                            </form>
                            <p>「いいね」 ${likes_count} 件 </p>

                        </c:when>
                        <c:otherwise>
                            <form method="get"
                                action="<c:url value='/likes' />" >
                                <button type= "submit"><i class="far fa-heart"></i> いいね</button>
                                <input type="hidden" name="report_id" value="${report.id}" />
                            </form>
                        <p>「いいね」 ${likes_count} 件 </p>
                        </c:otherwise>
                        </c:choose>






                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p>
            <a href="<c:url value="/reports/index" />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>