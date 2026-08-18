# API Mismatches Report

The following endpoints are called by the Angular frontend but do not have a matching `@RequestMapping` in the Spring Boot backend controllers.

## admission-analytics.service.ts

- **GET** `/admission-applications/analytics/monthly-trend` (Raw: ``${this.apiUrl}/analytics/monthly-trend`);`)
- **GET** `/admission-applications/analytics/program-breakdown` (Raw: ``${this.apiUrl}/analytics/program-breakdown`);`)

## applicant-choice.service.ts

- **GET** `/applicant-choices/admin/available-programs/{param}` (Raw: ``${this.apiUrl}/admin/available-programs/${configId}`);`)

## applicant-portal.service.ts

- **GET** `/applicant-portal/my-admit-card/pdf` (Raw: ``${this.apiUrl}/my-admit-card/pdf``)
- **GET** `/applicant-portal/my-admit-card/html` (Raw: ``${this.apiUrl}/my-admit-card/html``)

## auth.service.ts

- **GET** `/auth/me.pipe(` (Raw: ``${this.apiUrl}/me`).pipe(`)

## class-routine.service.ts

- **POST** `/class-routines/publish/{param}` (Raw: ``${this.apiUrl}/class-routines/publish/${semesterId}``)
- **GET** `/academic-calendar-events` (Raw: ``${this.apiUrl}/academic-calendar-events`);`)
- **GET** `/academic-calendar-events/{param}` (Raw: ``${this.apiUrl}/academic-calendar-events/${id}`);`)
- **POST** `/academic-calendar-events` (Raw: ``${this.apiUrl}/academic-calendar-events``)
- **PUT** `/academic-calendar-events/{param}` (Raw: ``${this.apiUrl}/academic-calendar-events/${id}``)
- **DELETE** `/academic-calendar-events/{param}` (Raw: ``${this.apiUrl}/academic-calendar-events/${id}`);`)
- **GET** `/academic-calendar-events/semester/{param}` (Raw: ``${this.apiUrl}/academic-calendar-events/semester/${semesterId}`);`)
- **GET** `/academic-calendar-events/upcoming` (Raw: ``${this.apiUrl}/academic-calendar-events/upcoming`);`)
- **GET** `/academic-calendar-events/holidays/{param}` (Raw: ``${this.apiUrl}/academic-calendar-events/holidays/${semesterId}`);`)

## club.service.ts

- **GET** `/clubs` (Raw: `this.apiUrl`)
- **GET** `/clubs/{param}` (Raw: ``${this.apiUrl}/${id}`);`)
- **POST** `/clubs` (Raw: `this.apiUrl`)
- **PUT** `/clubs/{param}` (Raw: ``${this.apiUrl}/${id}``)
- **DELETE** `/clubs/{param}` (Raw: ``${this.apiUrl}/${id}`);`)

## dashboard.service.ts

- **GET** `/dashboard/my.pipe(` (Raw: ``${this.apiUrl}/my`).pipe(`)

## enrollment-config.service.ts

- **GET** `/` (Raw: `this.apiUrl);`)

## enrollment.service.ts

- **POST** `/enrollments/enroll/{param}` (Raw: ``${this.apiUrl}/enroll/${allocationId}``)
- **GET** `/enrollments/stats` (Raw: ``${this.apiUrl}/stats`);`)

## file-upload.service.ts

- **POST** `/upload/{param}` (Raw: ``${this.apiUrl}/${module}``)
- **POST** `/upload/{param}/multiple` (Raw: ``${this.apiUrl}/${module}/multiple``)
- **DELETE** `/upload` (Raw: `this.apiUrl`)

## menu.service.ts

- **GET** `/menus/my.pipe(` (Raw: ``${this.apiUrl}/my`).pipe(`)
- **GET** `/` (Raw: `this.apiUrl);`)

## notification.service.ts

- **GET** `url` (Raw: `url);`)
- **GET** `/notifications/unread-count.pipe(` (Raw: ``${this.apiUrl}/unread-count`).pipe(`)

## pre-admission.service.ts

- **POST** `{param}/register` (Raw: ``${this.publicUrl}/register``)
- **GET** `{param}/status/{param}` (Raw: ``${this.publicUrl}/status/${registrationNumber}`);`)
- **GET** `{param}/register/{param}/pdf` (Raw: ``${this.publicUrl}/register/${registrationNumber}/pdf``)
- **GET** `{param}/register/{param}/qr-code` (Raw: ``${this.publicUrl}/register/${registrationNumber}/qr-code``)
- **GET** `{param}/register/{param}/payment-status` (Raw: ``${this.publicUrl}/register/${registrationNumber}/payment-status`);`)
- **POST** `{param}/register/{param}/pay` (Raw: ``${this.publicUrl}/register/${registrationNumber}/pay``)

## program-seat-allocation.service.ts

- **POST** `/program-seat-allocations/admin/reallocate/{param}` (Raw: ``${this.apiUrl}/admin/reallocate/${configId}``)
- **POST** `/program-seat-allocations/admin/expire-overdue/{param}` (Raw: ``${this.apiUrl}/admin/expire-overdue/${configId}``)
- **GET** `/program-seat-allocations/admin/demand-report/{param}` (Raw: ``${this.apiUrl}/admin/demand-report/${configId}`);`)
- **GET** `/program-seat-allocations/my-allocation` (Raw: ``${this.apiUrl}/my-allocation``)

## registration-config.service.ts

- **GET** `/` (Raw: `this.apiUrl);`)

## security-dashboard.service.ts

- **GET** `/security/dashboard/recent-activities` (Raw: ``${this.apiUrl}/recent-activities`);`)

## student-dashboard.service.ts

- **GET** `/` (Raw: `this.apiUrl);`)

## system-setting.service.ts

- **GET** `{param}/dropdowns` (Raw: ``${this.baseUrl}/dropdowns`);`)
- **GET** `this.baseUrl` (Raw: `this.baseUrl);`)
- **GET** `{param}/by-module/{param}` (Raw: ``${this.baseUrl}/by-module/${module}`);`)
- **GET** `{param}/by-key/{param}` (Raw: ``${this.baseUrl}/by-key/${key}`);`)
- **GET** `{param}/{param}` (Raw: ``${this.baseUrl}/${id}`);`)
- **GET** `{param}/public` (Raw: ``${this.baseUrl}/public`);`)
- **POST** `this.baseUrl (unresolved` (Raw: `this.baseUrl`)
- **PUT** `{param}/{param}` (Raw: ``${this.baseUrl}/${id}``)
- **PUT** `{param}/batch` (Raw: ``${this.baseUrl}/batch``)
- **DELETE** `{param}/{param}` (Raw: ``${this.baseUrl}/${id}`);`)
- **DELETE** `{param}/by-key/{param}` (Raw: ``${this.baseUrl}/by-key/${key}`);`)
- **POST** `{param}/reset/{param}` (Raw: ``${this.baseUrl}/reset/${module}``)
- **POST** `{param}/seed` (Raw: ``${this.baseUrl}/seed``)
- **GET** `{param}/system-info` (Raw: ``${this.baseUrl}/system-info`);`)
- **POST** `{param}/clear-cache` (Raw: ``${this.baseUrl}/clear-cache``)

## user-permission.service.ts

- **POST** `/user-permissions/bulk` (Raw: ``${this.apiUrl}/bulk``)

## user.service.ts

- **PUT** `/users/{param}/unlock` (Raw: ``${this.apiUrl}/${id}/unlock``)

## workflow.service.ts

- **GET** `url` (Raw: `url);`)

