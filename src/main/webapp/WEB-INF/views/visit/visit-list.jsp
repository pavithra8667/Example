<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Visits | EHR
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
                <span>
                    Clinical workspace
                </span>
            </div>
        </header>
        <main class="app-shell">
            <div class="page-head">
                <div>
                    <p class="eyebrow">
                        Visit management
                    </p>
                    <h1>
                        Patient visits
                    </h1>
                    <p>
                        Open, review, and complete patient encounters.
                    </p>
                </div>
                <a class="primary-btn" href="${pageContext.request.contextPath}/visits/new">
                    + Open visit
                </a>
            </div>
            <section class="card table-card">
                <div class="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>
                                    Visit
                                </th>
                                <th>
                                    Patient
                                </th>
                                <th>
                                Doctor
                                </th>
                                <th>
                                    Visit date
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
                            <c:forEach var="visit" items="${visits}">
                                <tr>
                                    <td>
                                        <strong>
                                            ${visit.visitCode}
                                        </strong>
                                    </td>
                                    <td>
                                        <strong>
                                            ${visit.patientName}
                                        </strong>
                                        <br>
                                        <span class="muted">
                                            ${visit.patientCode}
                                        </span>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty visit.doctorName}">
                                                ${visit.doctorName}
                                            </c:when>
                                            <c:otherwise>
                                                <span class="muted">Doctor record unavailable</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        ${visit.visitDate}
                                    </td>
                                    <td>
                                        <span class="badge badge-${visit.status == 'OPEN' ? 'open' : 'closed'}">
                                            ${visit.status}
                                        </span>
                                    </td>
                                    <td>
                                        <div class="actions">
                                            <a class="text-link" href="${pageContext.request.contextPath}/visits/details/${visit.id}">
                                                View visit
                                            </a>
                                            <c:if test="${visit.status == 'OPEN'}">
                                                <form class="compact-form" method="post" action="${pageContext.request.contextPath}/visits/close/${visit.id}">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                    <button class="danger-btn" type="submit">
                                                        Close
                                                    </button>
                                                </form>
                                            </c:if>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty visits}">
                                <tr>
                                    <td colspan="6" class="empty">
                                        No visits yet. Open a new visit to begin documenting care.
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
