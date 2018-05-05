package com.weimhc.config;

import static com.google.common.collect.Lists.newArrayList;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationCodeGrant;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = { "com.weimhc.api.modules", "com.weimhc.api.report",
		"com.weimhc.api.core" })
public class SwaggerConfig {
	@Value("${security.userOauth.clientId}")
	private String authClientId;

	@Value("${security.userOauth.clientSecret}")
	private String authClientSecret;

	@Value("${security.userOauth.type}")
	private String type;

	@Value("${security.userOauth.authorizationUrl}")
	private String authorizationUrl;

	@Value("${security.userOauth.tokenUrl}")
	private String tokenUrl;

	@Value("${security.userOauth.tokenName}")
	private String tokenName;

	@Value("${security.userOauth.scope.code}")
	private String scopeCode;

	@Value("${security.userOauth.scope.desc}")
	private String scopeDesc;

	@Value("${app.key}")
	private String appKey;

	@Value("${app.name}")
	private String appName;

	@Value("${app.desc}")
	private String appDesc;

	@Value("${app.version}")
	private String appVersion;

	@Value("${app.termsOfServiceUrl}")
	private String termsOfServiceUrl;

	@Value("${app.contact.name}")
	private String contactName;

	@Value("${app.contact.url}")
	private String contactUrl;

	@Value("${app.contact.email}")
	private String contactEmail;

	@Value("${app.license}")
	private String license;

	@Value("${app.licenseUrl}")
	private String licenseUrl;

	@Bean
	public Docket learnyApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.paths(PathSelectors.any()).build().pathMapping("/")
				.directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class).useDefaultResponseMessages(false)
				.securitySchemes(securitySchemes())
				.securityContexts(newArrayList(securityContext()));
	}

	private List<SecurityScheme> securitySchemes() {
		List<SecurityScheme> securityScheme = newArrayList();
		securityScheme.addAll(apiKeys());
		return securityScheme;
	}

	// new ApiKey("token", "token", "query")); //表示从query中传递token
	// new ApiKey("token", "token", "header")); //表示从header中传递token
	private List<ApiKey> apiKeys() {
		List<ApiKey> apiKeys = newArrayList(
				new ApiKey(AppHeader.ACCESS_TOKEN, AppHeader.ACCESS_TOKEN, "header"),
				new ApiKey(AppHeader.TIMESTAMP, AppHeader.TIMESTAMP, "header"),
				new ApiKey(AppHeader.VERSION, AppHeader.VERSION, "header"),
				new ApiKey(AppHeader.OS_TYPE, AppHeader.OS_TYPE, "header"),
				new ApiKey(AppHeader.DEVICE_ID, AppHeader.DEVICE_ID, "header"));
		return apiKeys;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(appName).description(appDesc)
				.termsOfServiceUrl("http://springfox.io")
				.contact(new Contact(contactName, contactUrl, contactEmail)).license(license)
				.licenseUrl(licenseUrl).version(appVersion).build();
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
		/* .forPaths(PathSelectors.regex("/*.*")) */
	}

	SecurityScheme oauth() {
		return new OAuthBuilder().name("petstore_auth").grantTypes(grantTypes()).scopes(scopes())
				.build();
	}

	List<AuthorizationScope> scopes() {
		return newArrayList(new AuthorizationScope[] { new AuthorizationScope("write:pets", "创建"),
				new AuthorizationScope("read:pets", "更新") });
	}

	List<GrantType> grantTypes() {
		GrantType authorizationCodeGrant = new AuthorizationCodeGrant(
				new TokenRequestEndpoint(authorizationUrl, authClientId, authClientSecret),
				new TokenEndpoint(tokenUrl, tokenName));
		List<GrantType> grants = newArrayList(authorizationCodeGrant);
		return grants;
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope[] apiKeyScopes = new AuthorizationScope[] {
				new AuthorizationScope("global", "accessEverything") };
		AuthorizationScope[] petstoreAuthScopes = new AuthorizationScope[] {
				new AuthorizationScope("write:pets", "创建"),
				new AuthorizationScope("read:pets", "更新") };
		SecurityReference securityReference = SecurityReference.builder().reference("petstore_auth")
				.scopes(petstoreAuthScopes).build();
		return newArrayList(new SecurityReference("api_key", apiKeyScopes), securityReference);
	}

	SecurityConfiguration security() {
		return new SecurityConfiguration("test-app-client-id", "test-app-realm", "test-app",
				"apiKey", null, null, null, null);
	}
}