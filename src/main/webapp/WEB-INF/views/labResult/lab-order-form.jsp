<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            New Lab Order | EHR
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
                        Create lab order
                    </h1>
                    <p>
                        Add a diagnostic test to the current visit.
                    </p>
                </div>
                <a class="secondary-btn" href="${pageContext.request.contextPath}/lab-orders?visitId=${visitId}">
                    Cancel
                </a>
            </div>
            <form class="card form-card" method="post" action="${pageContext.request.contextPath}/lab-orders/save">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="hidden" name="visitId" value="${visitId}">
                <label for="testName">
                    Test name
                </label>
                <input id="testName" name="testName" maxlength="100" placeholder="e.g. Complete blood count (CBC)" required>
                <div class="form-actions">
                    <button class="primary-btn" type="submit">
                        Create order
                    </button>
                </div>
            </form>
        </main>
    </body>
</html>
