<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Diagnoses | EHR
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
                        Clinical documentation
                    </p>
                    <h1>
                        Diagnoses
                    </h1>
                    <p>
                        Diagnoses documented for this visit.
                    </p>
                </div>
                <div class="actions">
                    <a class="secondary-btn" href="${pageContext.request.contextPath}/visits/details/${visitId}">
                        Visit overview
                    </a>
                    <a class="primary-btn" href="${pageContext.request.contextPath}/diagnoses/new?visitId=${visitId}">
                        + Add diagnosis
                    </a>
                </div>
            </div>
            <section class="card table-card">
                <div class="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>
                                    Code
                                </th>
                                <th>
                                    Description
                                </th>
                                <th>
                                    Type
                                </th>
                                <th>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="diagnosis" items="${diagnoses}">
                                <tr>
                                    <td>
                                        <strong>
                                            ${diagnosis.diagnosisCode}
                                        </strong>
                                    </td>
                                    <td>
                                        ${diagnosis.description}
                                    </td>
                                    <td>
                                        <span class="badge badge-primary">
                                            ${diagnosis.diagnosisType}
                                        </span>
                                    </td>
                                    <td>
                                        <form class="compact-form" method="post" action="${pageContext.request.contextPath}/diagnoses/delete/${diagnosis.id}">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                            <input type="hidden" name="visitId" value="${visitId}">
                                            <button class="danger-btn" type="submit">
                                                Delete
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty diagnoses}">
                                <tr>
                                    <td colspan="4" class="empty">
                                        No diagnoses have been added to this visit.
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
