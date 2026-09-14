<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Lab Orders | EHR
        </title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ehr-ui.css">
    </head>
    <body>
        <header class="topbar">
            <div class="app-shell">
                <div class="brand">
                    <span class="brand-mark">
                        +
                    </span>
                    CareFlow EHR
                </div>
            </div>
        </header>
        <main class="app-shell">
            <div class="page-head">
                <div>
                    <p class="eyebrow">
                        Investigations
                    </p>
                    <h1>
                        Lab orders
                    </h1>
                    <p>
                        Track requested tests and complete their results.
                    </p>
                </div>
                <div class="actions">
                    <a class="secondary-btn" href="${pageContext.request.contextPath}/visits/details/${visitId}">
                        Visit overview
                    </a>
                    <a class="primary-btn" href="${pageContext.request.contextPath}/lab-orders/new?visitId=${visitId}">
                        + New order
                    </a>
                </div>
            </div>
            <section class="card table-card">
                <div class="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>
                                    Test
                                </th>
                                <th>
                                    Status
                                </th>
                                <th>
                                    Actions
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${orders}">
                                <tr>
                                    <td>
                                        <strong>
                                            ${order.testName}
                                        </strong>
                                    </td>
                                    <td>
                                        <span class="badge badge-${order.status == 'COMPLETED' ? 'completed' : 'ordered'}">
                                            ${order.status}
                                        </span>
                                    </td>
                                    <td>
                                        <div class="actions">
                                            <a class="text-link" href="${pageContext.request.contextPath}/lab-results/new?labOrderId=${order.id}">
                                                Enter result
                                            </a>
                                            <form class="compact-form" method="post" action="${pageContext.request.contextPath}/lab-orders/delete/${order.id}">
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                <input type="hidden" name="visitId" value="${visitId}">
                                                <button class="danger-btn" type="submit">
                                                    Delete
                                                </button>
                                            </form>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty orders}">
                                <tr>
                                    <td colspan="3" class="empty">
                                        No lab orders have been created for this visit.
                                    </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </section>
        </main>
    </body>
</html>
