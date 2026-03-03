package repositoryImpl;

import entity.Member;
import repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository {

    private List<Member> members = new ArrayList<>();

    @Override
    public void save(Member member) {
        members.add(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return members.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        return members;
    }
}