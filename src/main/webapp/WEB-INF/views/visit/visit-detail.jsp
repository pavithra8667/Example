<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Visit ${visit.visitCode} | EHR
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
                        Visit record
                    </p>
                    <h1>
                        ${visit.visitCode}
                    </h1>
                    <p>
                        Encounter information and clinical documentation.
                    </p>
                </div>
                <a class="secondary-btn" href="${pageContext.request.contextPath}/visits">
                    ← All visits
                </a>
            </div>
            <section class="card form-card wide">
                <div class="summary-grid">
                    <div class="summary-item">
                        <span>
                            Patient
                        </span>
                        <strong>
                            ${visit.patientName}
                        </strong>
                        <div class="muted">
                            ${visit.patientCode}
                        </div>
                    </div>
                    <div class="summary-item">
                        <span>
                            Visit date
                        </span>
                        <strong>
                            ${visit.visitDate}
                        </strong>
                    </div>
                    <div class="summary-item">
                        <span>
                            Status
                        </span>
                        <strong>
                            <span class="badge badge-${visit.status == 'OPEN' ? 'open' : 'closed'}">
                                ${visit.status}
                            </span>
                        </strong>
                    </div>
                </div>
                <dl class="detail-list">
                    <div class="detail-row">
                        <dt>
                            Patient ID
                        </dt>
                        <dd class="mono">
                            ${visit.patientId}
                        </dd>
                    </div>
                    <div class="detail-row">
                        <dt>
                    Doctor
                        </dt>
                        <dd class="mono">
                    <c:choose>
                        <c:when test="${not empty visit.doctorName}">
                            ${visit.doctorName}
                        </c:when>
                        <c:otherwise>
                            <span class="muted">Doctor record unavailable</span>
                        </c:otherwise>
                    </c:choose>
                        </dd>
                    </div>
                </dl>
                <div class="module-grid">
                    <a class="module-link" href="${pageContext.request.contextPath}/diagnoses?visitId=${visit.id}">
                        Diagnoses
                        <small>
                            Record and review clinical diagnoses
                        </small>
                    </a>
                    <a class="module-link" href="${pageContext.request.contextPath}/lab-orders?visitId=${visit.id}">
                        Lab orders
                        <small>
                            Order tests and enter results
                        </small>
                    </a>
                    <a class="module-link" href="${pageContext.request.contextPath}/prescriptions?visitId=${visit.id}">
                        Prescriptions
                        <small>
                            Manage medication instructions
                        </small>
                    </a>
                </div>
            </section>
        </main>
    </body>
</html>
