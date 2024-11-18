package uagrm.bo.workflow.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import uagrm.bo.workflow.security.filter.JwtAuthenticationFilter;
import uagrm.bo.workflow.security.filter.JwtValidationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.GET, "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/logout").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/listar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/pacientes/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/pacientes/listar-pagina").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/pacientes/registrar").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/pacientes/actualizar/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/pacientes/eliminar/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medicos/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medicos/{id}/especialidades").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medicos/especialidad/{especialidadId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medico-especialidad/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medico-especialidad/listar-pagina").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medico-especialidad/medico/{medicoId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medico-especialidad/especialidad/{especialidadId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/medico-especialidad/asignar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/medicos/registrar").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/medicos/actualizar/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/medicos/eliminar/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/fichas/listar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/fichas/asignar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/fichas/agendarFicha").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/especialidad/listar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/especialidad/registrar").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/especialidad/actualizar/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/especialidad/eliminar/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/horarios/listar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/horarios/registrar").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/horarios/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medico-horarios/listar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/medico-horarios/asignar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/medico-horarios/generar-intervalos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/intervalo-horarios/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/consultorios/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/consultorios/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/consultorios/registrar").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/consultorios/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/consultorios/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/historiales").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/historiales/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/estado-intervalo").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/enfermeria/listar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/enfermeria/guardar").permitAll()
                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedOriginPatterns(List.of("**"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;
    }
}