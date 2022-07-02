package com.springproject.weathersharecommunity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member")
public class Member implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String pwd;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Column
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Likes> likesList = new ArrayList<>();

    @Column
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Scrape> scrapesList = new ArrayList<>();

    @Column
    private String profileUrl;

    @OneToMany(mappedBy = "member")
    private List<Reply> replies;
    @Builder
    public Member(String nickName, String userEmail, String pwd, String profileUrl, List<String> roles) {
        this.nickName = nickName;
        this.userEmail = userEmail;
        this.pwd = pwd;
        this.profileUrl = profileUrl;
        this.roles = roles;
    }

    public void mappingBoardLike(Likes likes) {
        this.likesList.add(likes);
    }

    public void updateProfile(String profileUrl) {
        this.profileUrl = profileUrl;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return nickName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
