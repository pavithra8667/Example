<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Prescription | EHR
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
                        Prescription details
                    </h1>
                    <p class="mono">
                        ${prescription.prescriptionId}
                    </p>
                </div>
                <a class="secondary-btn" href="${pageContext.request.contextPath}/prescriptions?visitId=${prescription.visitId}">
                    ← Prescriptions
                </a>
            </div>
            <section class="card table-card">
                <div class="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>
                                    Medicine
                                </th>
                                <th>
                                    Dosage
                                </th>
                                <th>
                                    Frequency
                                </th>
                                <th>
                                    Duration
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${prescription.items}">
                                <tr>
                                    <td>
                                        <strong>
                                            ${item.medicineName}
                                        </strong>
                                    </td>
                                    <td>
                                        ${item.dosage}
                                    </td>
                                    <td>
                                        ${item.frequency}
                                    </td>
                                    <td>
                                        ${item.durationDays} days
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty prescription.items}">
                                <tr>
                                    <td colspan="4" class="empty">
                                        No medication items are recorded.
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
