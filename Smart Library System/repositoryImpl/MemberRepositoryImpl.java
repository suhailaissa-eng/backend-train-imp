package repositoryImpl;

import entity.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import repository.MemberRepository;

public class MemberRepositoryImpl implements MemberRepository {

    private static MemberRepositoryImpl instance;
    private List<Member> members = new ArrayList<>();

    private MemberRepositoryImpl() {}

    public static synchronized MemberRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new MemberRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void save(Member member) { members.add(member); }

    @Override
    public Optional<Member> findById(Long id) {
        return members.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    @Override
    public List<Member> findAll() { return members; }
}