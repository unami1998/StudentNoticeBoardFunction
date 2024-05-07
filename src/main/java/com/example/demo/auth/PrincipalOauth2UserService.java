//package com.example.demo.auth;
//
//import com.example.demo.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PrincipalOauth2UserService {
//    @Autowired
//    private StudentRepository studentRepository;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        OAuth2UserInfo oAuth2UserInfo = null;
//        String provider = userRequest.getClientRegistration().getRegistrationId();
//
//        if(provider.equals("google")){
//            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
//        }
//        else if(provider.equals("naver")){
//            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
//        }
//        else if(provider.equals("kakao")){	//추가
//            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
//        }
//
//        String providerId = oAuth2UserInfo.getProviderId();
//        String username = provider+"_"+providerId;
//
//        String uuid = UUID.randomUUID().toString().substring(0, 6);
//        String password = bCryptPasswordEncoder.encode("패스워드"+uuid);
//
//        String email = oAuth2UserInfo.getEmail();
//        Role role = Role.ROLE_USER;
//
//        User byUsername = userRepository.findByUsername(username);
//
//        //DB에 없는 사용자라면 회원가입처리
//        if(byUsername == null){
//            byUsername = User.oauth2Register()
//                    .username(username).password(password).email(email).role(role)
//                    .provider(provider).providerId(providerId)
//                    .build();
//            userRepository.save(byUsername);
//        }
//
//        return new PrincipalDetails(byUsername, oAuth2UserInfo);
//    }
//
//}
