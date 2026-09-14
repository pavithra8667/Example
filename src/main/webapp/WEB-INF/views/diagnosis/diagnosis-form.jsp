<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Add Diagnosis | EHR
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
                        Add diagnosis
                    </h1>
                    <p>
                        Document a diagnosis for the current visit.
                    </p>
                </div>
                <a class="secondary-btn" href="${pageContext.request.contextPath}/diagnoses?visitId=${visitId}">
                    Cancel
                </a>
            </div>
            <form class="card form-card" method="post" action="${pageContext.request.contextPath}/diagnoses/save">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="hidden" name="visitId" value="${visitId}">
                <div class="field-grid">
                    <div class="field">
                        <label for="diagnosisCode">
                            Diagnosis code
                        </label>
                        <input id="diagnosisCode" name="diagnosisCode" maxlength="20" placeholder="e.g. J06.9" required>
                    </div>
                    <div class="field">
                        <label for="diagnosisType">
                            Diagnosis type
                        </label>
                        <select id="diagnosisType" name="diagnosisType" required>
                            <option value="">
                                Select a type
                            </option>
                            <c:forEach var="type" items="${types}">
                                <option value="${type}">
                                    ${type}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="field full">
                        <label for="description">
                            Clinical description
                        </label>
                        <textarea id="description" name="description" maxlength="255" placeholder="Describe the diagnosis" required>
                        </textarea>
                    </div>
                </div>
                <div class="form-actions">
                    <button class="primary-btn" type="submit">
                        Save diagnosis
                    </button>
                    <a class="text-link" href="${pageContext.request.contextPath}/diagnoses?visitId=${visitId}">
                        View diagnoses
                    </a>
                </div>
            </form>
        </main>
    </body>
</html>
