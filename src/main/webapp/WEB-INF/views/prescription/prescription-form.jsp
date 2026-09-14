<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Prescription | EHR</title>
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
            <p class="eyebrow">Medication management</p>
            <h1>Create prescription</h1>
            <p>Add clear medication and administration instructions.</p>
        </div>
        <a class="secondary-btn"
           href="${pageContext.request.contextPath}/prescriptions?visitId=${visitId}">Cancel</a>
    </div>

    <form class="card form-card wide"
          method="post"
          action="${pageContext.request.contextPath}/prescriptions/save">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="hidden" name="visitId" value="${visitId}">

        <h2 class="form-section-title">Medication items</h2>
        <p class="form-help">Add one row for each prescribed medicine.</p>

        <div id="medicines">
            <div class="medicine-row">
                <div class="field-grid">
                    <div class="field">
                        <label>Medicine</label>
                        <input name="medicineName" placeholder="Medicine name" required>
                    </div>
                    <div class="field">
                        <label>Dosage</label>
                        <input name="dosage" placeholder="e.g. 500 mg" required>
                    </div>
                    <div class="field">
                        <label>Frequency</label>
                        <input name="frequency" placeholder="e.g. 1-0-1" required>
                    </div>
                    <div class="field">
                        <label>Duration (days)</label>
                        <input type="number" name="durationDays" min="1" max="90" required>
                    </div>
                </div>
            </div>
        </div>

        <div class="actions" style="margin-top: 16px">
            <button class="secondary-btn" type="button" onclick="addMedicine()">
                + Add another medicine
            </button>
        </div>

        <div class="form-actions">
            <button class="primary-btn" type="submit">Save prescription</button>
        </div>
    </form>
</main>

<script>
    function addMedicine() {
        const row = document.createElement('div');
        row.className = 'medicine-row';
        row.innerHTML = `
            <div class="field-grid">
                <div class="field">
                    <label>Medicine</label>
                    <input name="medicineName" placeholder="Medicine name" required>
                </div>
                <div class="field">
                    <label>Dosage</label>
                    <input name="dosage" placeholder="e.g. 500 mg" required>
                </div>
                <div class="field">
                    <label>Frequency</label>
                    <input name="frequency" placeholder="e.g. 1-0-1" required>
                </div>
                <div class="field">
                    <label>Duration (days)</label>
                    <input type="number" name="durationDays" min="1" max="90" required>
                </div>
            </div>
            <button class="danger-btn remove-item" type="button"
                    onclick="this.parentElement.remove()">
                Remove medicine
            </button>`;
        document.getElementById('medicines').appendChild(row);
    }
</script>
</body>
</html>
