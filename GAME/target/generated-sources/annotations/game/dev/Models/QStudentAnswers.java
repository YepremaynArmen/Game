package game.dev.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudentAnswers is a Querydsl query type for StudentAnswers
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudentAnswers extends EntityPathBase<StudentAnswers> {

    private static final long serialVersionUID = -906216023L;

    public static final QStudentAnswers studentAnswers = new QStudentAnswers("studentAnswers");

    public final StringPath answer = createString("answer");

    public final DatePath<java.sql.Date> aprovedAt = createDate("aprovedAt", java.sql.Date.class);

    public final ComparablePath<java.util.UUID> aprovedBy = createComparable("aprovedBy", java.util.UUID.class);

    public final DatePath<java.sql.Date> createdAt = createDate("createdAt", java.sql.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> progress = createNumber("progress", Long.class);

    public final NumberPath<Long> questionId = createNumber("questionId", Long.class);

    public final BooleanPath result = createBoolean("result");

    public final StringPath status = createString("status");

    public final ComparablePath<java.util.UUID> userId = createComparable("userId", java.util.UUID.class);

    public QStudentAnswers(String variable) {
        super(StudentAnswers.class, forVariable(variable));
    }

    public QStudentAnswers(Path<? extends StudentAnswers> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudentAnswers(PathMetadata metadata) {
        super(StudentAnswers.class, metadata);
    }

}

