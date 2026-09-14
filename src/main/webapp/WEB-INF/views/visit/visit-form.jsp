<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Open Visit | EHR</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/ehr-ui.css">
</head>
<body>
<header class="topbar">
    <div class="app-shell">
        <div class="brand">
            <span class="brand-mark">+</span>
            CareFlow EHR
        </div>
    </div>
</header>

<main class="app-shell">
    <div class="page-head">
        <div>
            <p class="eyebrow">Visit management</p>
            <h1>Open a new visit</h1>
            <p>Start a clinical encounter by selecting the patient and clinician.</p>
        </div>
        <a class="secondary-btn"
           href="${pageContext.request.contextPath}/visits">Cancel</a>
    </div>

    <form class="card form-card"
          method="post"
          action="${pageContext.request.contextPath}/visits/save">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}">

        <h2 class="form-section-title">Encounter details</h2>
        <p class="form-help">The identifiers are submitted securely in the background.</p>

        <div class="field-grid">
            <div class="field">
                <label for="patientId">Patient</label>
                <select id="patientId" name="patientId" required>
                    <option value="">Select a patient</option>
                    <c:forEach var="patient" items="${patients}">
                        <option value="${patient.id}">
                            ${patient.name} (${patient.patientCode})
                        </option>
                    </c:forEach>
                </select>
                <c:if test="${empty patients}">
                    <span class="muted">No patients are available. Register a patient first.</span>
                </c:if>
            </div>

            <div class="field">
                <label for="doctorId">Doctor</label>
                <select id="doctorId" name="doctorId" required>
                    <option value="">Select a doctor</option>
                    <c:forEach var="doctor" items="${doctors}">
                        <option value="${doctor.id}">${doctor.username}</option>
                    </c:forEach>
                </select>
                <c:if test="${empty doctors}">
                    <span class="muted">No active doctors are available.</span>
                </c:if>
            </div>

            <div class="field full">
                <label for="visitDate">
                    Visit date &amp; time <span class="muted">(optional)</span>
                </label>
                <input id="visitDate" name="visitDate" type="datetime-local">
                <span class="muted">Leave blank to use the system’s default encounter time.</span>
            </div>
        </div>

        <div class="form-actions">
            <button class="primary-btn" type="submit">Open visit</button>
            <a class="text-link"
               href="${pageContext.request.contextPath}/visits">Back to visits</a>
        </div>
    </form>
</main>
</body>
</html>
