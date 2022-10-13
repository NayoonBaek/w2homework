package com.sparta.w2homework.dto;

import com.sparta.w2homework.entity.Authority;
import com.sparta.w2homework.entity.Member;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class MemberRequestDto {
//    @Size(min=4,max=12,message = "length")
    @Pattern(regexp = "[a-zA-Z0-9]{4,12}", message = "닉네임은 최소 4자 이상, 12자 이하 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성")
    private String nickname;
    @Pattern(regexp = "[a-zA-Z0-9]{4,32}", message = "비밀번호는 최소 4자 이상이며, 32자 이하 알파벳 소문자(a~z), 숫자(0~9) 로 구성")
    private String password;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(nickname, password);
    }
}