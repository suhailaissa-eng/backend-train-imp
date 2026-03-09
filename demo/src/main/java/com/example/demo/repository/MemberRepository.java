package com.example.demo.repository;

import com.example.demo.entity.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    Optional<Member> findById(Long id);

    List<Member> findAll();
}