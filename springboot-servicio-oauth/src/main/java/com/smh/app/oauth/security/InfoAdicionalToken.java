package com.smh.app.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.smh.app.oauth.servicios.IUsuarioService;
import com.smh.app.usuario.commons.modelo.entidades.Usuario;

@Component
public class InfoAdicionalToken implements TokenEnhancer{

	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
