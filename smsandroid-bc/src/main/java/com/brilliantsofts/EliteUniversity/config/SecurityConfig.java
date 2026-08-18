package com.brilliantsofts.EliteUniversity.config;

import com.brilliantsofts.EliteUniversity.security.CustomAccessDeniedHandler;
import com.brilliantsofts.EliteUniversity.security.CustomUserDetailsService;
import com.brilliantsofts.EliteUniversity.security.JwtAuthenticationEntryPoint;
import com.brilliantsofts.EliteUniversity.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Value("${app.cors.allowed-origins:http://localhost:4200}")
    private String allowedOrigins;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler))
                .authorizeHttpRequests(auth -> auth
                        // Public Auth Endpoints
                        .requestMatchers("/api/auth/**").permitAll()

                        // Public pre-admission registration endpoints
                        .requestMatchers("/api/pre-admission/**", "/api/preadmission/**").permitAll()

                        // Public GET endpoints for visitor home page, circulars, published results & programs
                        .requestMatchers(HttpMethod.GET, "/api/admission-circulars/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admission-merit-lists/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admission-merit-list-entries/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admission-tests/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/faculties/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/departments/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/programs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/notices/**").permitAll()

                        // User Management: SUPER_ADMIN, ADMIN
                        .requestMatchers("/api/users/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Academic Structure: SUPER_ADMIN, ADMIN
                        .requestMatchers("/api/faculties/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/departments/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/programs/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Employees / Staff: SUPER_ADMIN, ADMIN
                        .requestMatchers("/api/employees/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Courses & LMS: SUPER_ADMIN, ADMIN, TEACHER, STUDENT (read); TEACHER write
                        .requestMatchers("/api/courses/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/course-modules/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/live-classes/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/video-lectures/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/pdf-notes/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")

                        // Examinations & Results: SUPER_ADMIN, ADMIN, TEACHER, STUDENT
                        .requestMatchers("/api/examinations/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/examination-results/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/result-sheets/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT", "STAFF", "DEPARTMENT_HEAD", "ADVISOR", "ADMISSION_OFFICER")
                        .requestMatchers("/api/academic-results/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "APPLICANT")

                        // Admissions: SUPER_ADMIN, ADMIN, APPLICANT
                        .requestMatchers("/api/admission-results/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "APPLICANT")
                        .requestMatchers("/api/applicants/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "APPLICANT")

                        // Students: SUPER_ADMIN, ADMIN, TEACHER, STAFF, STUDENT
                        .requestMatchers("/api/students/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT")

                        // Library: role-based
                        .requestMatchers("/api/books/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "LIBRARIAN", "STAFF", "TEACHER", "STUDENT")
                        .requestMatchers("/api/book-issues/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "LIBRARIAN", "STAFF", "STUDENT")

                        // Notices: authenticated (any role for read; write restricted by @PreAuthorize)
                        .requestMatchers("/api/notices/**").authenticated()

                        // Payments: restricted roles (STUDENT for initiate)
                        .requestMatchers("/api/payments/ipn", "/api/payments/callback").permitAll()
                        .requestMatchers("/api/payments/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF", "ACCOUNTS_OFFICER", "STUDENT", "APPLICANT")

                        // Security management: SUPER_ADMIN, ADMIN
                        .requestMatchers("/api/roles/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/permissions/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/menus/my").authenticated()
                        .requestMatchers("/api/menus/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/audit-logs/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/activity-logs/recent").authenticated()
                        .requestMatchers("/api/activity-logs/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Enrollments: restricted roles
                        .requestMatchers("/api/enrollments/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT")

                        // Academic Sessions: role-based
                        .requestMatchers("/api/academic-sessions/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT")

                        // Choice Filling & Admission Pipeline
                        .requestMatchers("/api/choice-filling-configs/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER", "APPLICANT")
                        .requestMatchers("/api/applicant-choices/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER", "APPLICANT")
                        .requestMatchers("/api/admission-confirmations/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER", "APPLICANT")
                        .requestMatchers("/api/admission-fee-collection/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admission-dashboard/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admission-test-dashboard/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")

                        // Admissions management: SUPER_ADMIN, ADMIN, ADMISSION_OFFICER
                        .requestMatchers("/api/admission-tests/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admission-test-questions/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admission-test-results/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admission-merit-lists/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admission-waiting-lists/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admission-applications/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admission-candidates/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admission-circulars/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")

                        // Admission Pipeline Batch 2: SUPER_ADMIN, ADMIN, ADMISSION_OFFICER
                        .requestMatchers("/api/admission-campaigns/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admission-enrollments/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admission-interviews/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admission-offer-letters/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admission-attendance/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/admit-cards/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/application-reviews/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/seat-allocations/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/seat-allocation-configs/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER", "APPLICANT")
                        .requestMatchers("/api/program-seat-configs/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")

                        // Finance management: SUPER_ADMIN, ADMIN, ACCOUNTS_OFFICER, STUDENT (read own)
                        .requestMatchers("/api/fee-types/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "ACCOUNTS_OFFICER")
                        .requestMatchers("/api/fee-structures/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ACCOUNTS_OFFICER")
                        .requestMatchers("/api/discounts/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "ACCOUNTS_OFFICER")
                        .requestMatchers("/api/fines/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ACCOUNTS_OFFICER", "STUDENT")
                        .requestMatchers("/api/invoices/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ACCOUNTS_OFFICER", "STUDENT")
                        .requestMatchers("/api/student-fees/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ACCOUNTS_OFFICER", "STUDENT")
                        .requestMatchers("/api/transactions/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "ACCOUNTS_OFFICER")
                        .requestMatchers("/api/accounts/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "ACCOUNTS_OFFICER")

                        // Student Portal
                        .requestMatchers("/api/guardians/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT")
                        .requestMatchers("/api/medical-info/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT")
                        .requestMatchers("/api/student-documents/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT")
                        .requestMatchers("/api/student-attendance/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT")
                        .requestMatchers("/api/student-promotions/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER")
                        .requestMatchers("/api/alumni/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/certificates/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/transcripts/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/disciplinary-records/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER")
                        .requestMatchers("/api/applicant-portal/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "APPLICANT")
                        .requestMatchers("/api/student-portal/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "STUDENT")

                        // LMS Module
                        .requestMatchers("/api/assignments/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/assignment-submissions/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/course-materials/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/online-classes/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")

                        // Registration/Enrollment Module
                        .requestMatchers("/api/course-registrations/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/semester-registrations/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/enrollment-configs/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/advisor-approvals/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER")

                        // Examination Module
                        .requestMatchers("/api/exam-schedules/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/exam-centers/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER")
                        .requestMatchers("/api/grade-rules/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER")
                        .requestMatchers("/api/marks/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER")
                        .requestMatchers("/api/results/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")

                        // Teacher Module
                        .requestMatchers("/api/teachers/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF")
                        .requestMatchers("/api/employee-attendance/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF")
                        .requestMatchers("/api/awards/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF")
                        .requestMatchers("/api/publications/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF")
                        .requestMatchers("/api/teacher-promotions/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Academic Module
                        .requestMatchers("/api/academic-calendars/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT")
                        .requestMatchers("/api/academic-policies/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER")
                        .requestMatchers("/api/academic-dashboard/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER")
                        .requestMatchers("/api/batches/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF")
                        .requestMatchers("/api/campuses/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/class-routines/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/semester-routines/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")

                        // Event Module
                        .requestMatchers("/api/events/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT")
                        .requestMatchers("/api/event-registrations/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT")

                        // Communication Module
                        .requestMatchers("/api/notifications/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT", "APPLICANT")
                        .requestMatchers("/api/messages/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT")
                        .requestMatchers("/api/announcements/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF")

                        // RBAC Module
                        .requestMatchers("/api/role-permissions/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/user-roles/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/user-permissions/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Security Module
                        .requestMatchers("/api/login-sessions/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/security/dashboard/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Library Module
                        .requestMatchers("/api/book-categories/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "LIBRARIAN")
                        .requestMatchers("/api/book-returns/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "LIBRARIAN", "STUDENT")

                        // Hostel Module
                        .requestMatchers("/api/hostels/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF")
                        .requestMatchers("/api/hostel-allocations/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF", "STUDENT")

                        // Transport Module
                        .requestMatchers("/api/vehicles/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF")
                        .requestMatchers("/api/routes/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF")
                        .requestMatchers("/api/transport-allocations/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "STAFF", "STUDENT")

                        // Administration Module
                        .requestMatchers("/api/administrations/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/administration-divisions/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Scheduling Module
                        .requestMatchers("/api/semesters/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT")
                        .requestMatchers("/api/sections/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF")
                        .requestMatchers("/api/orientation/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF", "STUDENT")

                        // Attachments & Comments (generic)
                        .requestMatchers("/api/attachments/**").authenticated()
                        .requestMatchers("/api/comments/**").authenticated()

                        // Course Assignments: SUPER_ADMIN, ADMIN, TEACHER, STUDENT
                        .requestMatchers("/api/course-assignments/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")

                        // Department Allocations: SUPER_ADMIN, ADMIN
                        .requestMatchers("/api/department-allocations/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Document Verification & Eligibility: SUPER_ADMIN, ADMIN, ADMISSION_OFFICER
                        .requestMatchers("/api/document-verifications/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/eligibility-criteria/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")
                        .requestMatchers("/api/eligibility-verifications/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")

                        // Reports & Templates: SUPER_ADMIN, ADMIN
                        .requestMatchers("/api/generated-reports/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/report-templates/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Payroll: SUPER_ADMIN, ADMIN, ACCOUNTS_OFFICER
                        .requestMatchers("/api/payrolls/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "ACCOUNTS_OFFICER")

                        // Program Seat Allocations: SUPER_ADMIN, ADMIN, ADMISSION_OFFICER
                        .requestMatchers("/api/program-seat-allocations/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER", "APPLICANT")

                        // Rooms: SUPER_ADMIN, ADMIN
                        .requestMatchers("/api/rooms/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Sports: SUPER_ADMIN, ADMIN, TEACHER, STUDENT
                        .requestMatchers("/api/sports/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")

                        // System Settings: SUPER_ADMIN, ADMIN
                        .requestMatchers("/api/system-settings/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Timeline: SUPER_ADMIN, ADMIN, TEACHER, STAFF
                        .requestMatchers("/api/timeline/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STAFF")

                        // Universities: SUPER_ADMIN, ADMIN
                        .requestMatchers("/api/universities/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Workflows: SUPER_ADMIN, ADMIN
                        .requestMatchers("/api/workflows/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Admission Test Attempts: SUPER_ADMIN, ADMIN, APPLICANT, ADMISSION_OFFICER
                        .requestMatchers("/api/admission-test-attempts/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "APPLICANT", "ADMISSION_OFFICER")

                        // Admission Requirements: SUPER_ADMIN, ADMIN, ADMISSION_OFFICER
                        .requestMatchers("/api/admission-requirements/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "ADMISSION_OFFICER")

                        // Curriculum, Credit Rules, Prerequisites: SUPER_ADMIN, ADMIN, TEACHER
                        .requestMatchers("/api/curricula/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER")
                        .requestMatchers("/api/credit-rules/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER")
                        .requestMatchers("/api/prerequisites/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER")

                        // Registration & Enrollment: SUPER_ADMIN, ADMIN, TEACHER, STUDENT
                        .requestMatchers("/api/registrations/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/registration-configs/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers("/api/semester-enrollments/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/student-enrollments/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")

                        // Student Profile & ID: SUPER_ADMIN, ADMIN, TEACHER, STUDENT
                        .requestMatchers("/api/student-profiles/**")
                        .hasAnyRole("SUPER_ADMIN", "ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/student-id-generation/**").hasAnyRole("SUPER_ADMIN", "ADMIN")

                        // Dashboards: authenticated
                        .requestMatchers("/api/dashboard/**", "/api/dashboards/**").authenticated()

                        // Default rule for all other endpoints
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Permissive CORS for payment gateway server-to-server callbacks (SSLCommerz, IPN, etc.)
        // These requests originate from the payment gateway's servers, not the browser,
        // so they won't match our allowed-origins list — we must allow any origin here.
        CorsConfiguration paymentGatewayConfig = new CorsConfiguration();
        paymentGatewayConfig.setAllowedOriginPatterns(List.of("*"));
        paymentGatewayConfig.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
        paymentGatewayConfig.setAllowedHeaders(List.of("*"));
        paymentGatewayConfig.setAllowCredentials(false);
        paymentGatewayConfig.setMaxAge(3600L);

        // Strict CORS for all other endpoints — only allow our known front-end origins
        CorsConfiguration configuration = new CorsConfiguration();
        List<String> origins = Arrays.stream(allowedOrigins.split(","))
                .map(String::trim)
                .toList();
        configuration.setAllowedOrigins(origins);
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Register payment gateway endpoints first (more specific paths take priority)
        source.registerCorsConfiguration("/api/payments/callback", paymentGatewayConfig);
        source.registerCorsConfiguration("/api/payments/ipn", paymentGatewayConfig);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
