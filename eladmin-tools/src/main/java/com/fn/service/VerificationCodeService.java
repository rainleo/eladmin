package com.fn.service;

import com.fn.domain.VerificationCode;
import com.fn.domain.vo.EmailVo;

/**
 * @author leo
 * @date 2018-12-26
 */
public interface VerificationCodeService {

    /**
     * 发送邮件验证码
     * @param code
     */
    EmailVo sendEmail(VerificationCode code);

    /**
     * 验证
     * @param code
     */
    void validated(VerificationCode code);
}
