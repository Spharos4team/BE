package com.spharos.ssgpoint;

import com.spharos.ssgpoint.point.application.PointServiceImpl;
import com.spharos.ssgpoint.point.domain.*;
import com.spharos.ssgpoint.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.spharos.ssgpoint.point.domain.PointStatusType.적립;
import static com.spharos.ssgpoint.point.domain.PointType.결제;

@SpringBootTest
class SsgpointApplicationTests {

}
