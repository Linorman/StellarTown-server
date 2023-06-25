package com.hllwz.stellartownserver.service.impl;

import com.hllwz.stellartownserver.mapper.RedisCache;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Deprecated
public class LogoutServiceImpl implements LogoutHandler {

  private final RedisCache tokenRepository;

  @Override
  public void logout(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication
  ) {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    jwt = authHeader.substring(7);
    boolean isDelete = tokenRepository.deleteObjectByValue(jwt);
    if (isDelete) {
      SecurityContextHolder.clearContext();
    }
  }
}
