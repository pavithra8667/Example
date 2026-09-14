<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Lab Result | EHR
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
                        Investigation result
                    </p>
                    <h1>
                        ${result.testName}
                    </h1>
                    <p>
                        Recorded ${result.resultDate}
                    </p>
                </div>
                <a class="secondary-btn" href="javascript:history.back()">
                    ← Back
                </a>
            </div>
            <section class="card form-card">
                <div class="summary-grid">
                    <div class="summary-item">
                        <span>
                            Result
                        </span>
                        <strong>
                            ${result.resultValue} ${result.unit}
                        </strong>
                    </div>
                    <div class="summary-item">
                        <span>
                            Flag
                        </span>
                        <strong>
                            <span class="badge badge-${result.flag == 'NORMAL' ? 'normal' : 'abnormal'}">
                                ${result.flag}
                            </span>
                        </strong>
                    </div>
                    <div class="summary-item">
                        <span>
                            Date
                        </span>
                        <strong>
                            ${result.resultDate}
                        </strong>
                    </div>
                </div>
                <dl class="detail-list">
                    <div class="detail-row">
                        <dt>
                            Reference range
                        </dt>
                        <dd>
                            ${result.referenceRange}
                        </dd>
                    </div>
                    <div class="detail-row">
                        <dt>
                            Lab order ID
                        </dt>
                        <dd class="mono">
                            ${result.labOrderId}
                        </dd>
                    </div>
                </dl>
            </section>
        </main>
    </body>
</html>
