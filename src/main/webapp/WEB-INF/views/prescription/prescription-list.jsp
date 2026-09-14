<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Prescriptions | EHR
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
                        Medication management
                    </p>
                    <h1>
                        Prescriptions
                    </h1>
                    <p>
                        Medication plans for this visit.
                    </p>
                </div>
                <div class="actions">
                    <a class="secondary-btn" href="${pageContext.request.contextPath}/visits/details/${visitId}">
                        Visit overview
                    </a>
                    <a class="primary-btn" href="${pageContext.request.contextPath}/prescriptions/new?visitId=${visitId}">
                        + New prescription
                    </a>
                </div>
            </div>
            <section class="card table-card">
                <div class="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>
                                    Prescription
                                </th>
                                <th>
                                    Actions
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="prescription" items="${prescriptions}">
                                <tr>
                                    <td>
                                        <strong class="mono">
                                            ${prescription.prescriptionId}
                                        </strong>
                                    </td>
                                    <td>
                                        <div class="actions">
                                            <a class="text-link" href="${pageContext.request.contextPath}/prescriptions/view/${prescription.prescriptionId}">
                                                View prescription
                                            </a>
                                            <form class="compact-form" method="post" action="${pageContext.request.contextPath}/prescriptions/delete/${prescription.prescriptionId}">
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
                            <c:if test="${empty prescriptions}">
                                <tr>
                                    <td colspan="2" class="empty">
                                        No prescriptions have been created for this visit.
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
