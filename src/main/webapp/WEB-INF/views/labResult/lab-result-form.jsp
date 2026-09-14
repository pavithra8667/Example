<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Enter Result | EHR
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
                        Enter lab result
                    </h1>
                    <p>
                        Record the measured result and reference interval.
                    </p>
                </div>
                <a class="secondary-btn" href="javascript:history.back()">
                    Cancel
                </a>
            </div>
            <form class="card form-card" method="post" action="${pageContext.request.contextPath}/lab-results/save">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="hidden" name="labOrderId" value="${labOrderId}">
                <div class="note">
                    <strong>
                        Note
                    </strong>
                    <span>
                        The result flag is calculated automatically when the result is saved.
                    </span>
                </div>
                <div class="field-grid">
                    <div class="field">
                        <label for="resultValue">
                            Result value
                        </label>
                        <input id="resultValue" name="resultValue" placeholder="e.g. 12.5" required>
                    </div>
                    <div class="field">
                        <label for="unit">
                            Unit
                            <span class="muted">
                                (optional)
                            </span>
                        </label>
                        <input id="unit" name="unit" placeholder="e.g. g/dL">
                    </div>
                    <div class="field">
                        <label for="referenceRange">
                            Reference range
                        </label>
                        <input id="referenceRange" name="referenceRange" placeholder="e.g. 12 - 16" required>
                    </div>
                    <div class="field">
                        <label for="resultDate">
                            Result date
                        </label>
                        <input id="resultDate" name="resultDate" type="date" value="${today}" required>
                    </div>
                </div>
                <div class="form-actions">
                    <button class="primary-btn" type="submit">
                        Save result
                    </button>
                </div>
            </form>
        </main>
    </body>
</html>
