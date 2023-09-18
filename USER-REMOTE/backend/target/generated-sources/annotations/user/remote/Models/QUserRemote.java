package user.remote.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserRemote is a Querydsl query type for UserRemote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserRemote extends EntityPathBase<UserRemote> {

    private static final long serialVersionUID = -687137008L;

    public static final QUserRemote userRemote = new QUserRemote("userRemote");

    public final DatePath<java.sql.Date> birthDay = createDate("birthDay", java.sql.Date.class);

    public final DatePath<java.sql.Date> changeDate = createDate("changeDate", java.sql.Date.class);

    public final DatePath<java.sql.Date> createDate = createDate("createDate", java.sql.Date.class);

    public final StringPath firstName = createString("firstName");

    public final StringPath lastName = createString("lastName");

    public final StringPath mail = createString("mail");

    public final StringPath patronymicName = createString("patronymicName");

    public final StringPath phone = createString("phone");

    public final DatePath<java.sql.Date> registerDate = createDate("registerDate", java.sql.Date.class);

    public final NumberPath<Integer> sex = createNumber("sex", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final ComparablePath<java.util.UUID> userId = createComparable("userId", java.util.UUID.class);

    public QUserRemote(String variable) {
        super(UserRemote.class, forVariable(variable));
    }

    public QUserRemote(Path<? extends UserRemote> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRemote(PathMetadata metadata) {
        super(UserRemote.class, metadata);
    }

}

