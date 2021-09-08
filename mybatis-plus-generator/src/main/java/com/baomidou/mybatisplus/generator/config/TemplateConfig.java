/*
 * Copyright (c) 2011-2021, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.generator.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.extra.ExtraGenerator;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * 模板路径配置项
 *
 * @author tzg hubin
 * @since 2017-06-17
 */
public class TemplateConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateConfig.class);

    /**
     * 设置实体模板路径
     */
    private String entity;

    private List<ExtraGenerator> entityExtra = Collections.emptyList();

    /**
     * 设置实体模板路径(kotlin模板)
     */
    private String entityKt;

    /**
     * 设置Service模板路径
     */
    private String service;

    private List<ExtraGenerator> serviceExtra = Collections.emptyList();

    /**
     * 设置ServiceImpl模板路径
     */
    private String serviceImpl;
    private List<ExtraGenerator> serviceImplExtra = Collections.emptyList();
    /**
     * 设置Mapper模板路径
     */
    private String mapper;

    private List<ExtraGenerator> mapperExtra = Collections.emptyList();

    private String xml;

    private List<ExtraGenerator> xmlExtra = Collections.emptyList();
    /**
     * 设置控制器模板路径
     */
    private String controller;
    /**
     * 是否禁用实体模板（默认 false）
     */
    private boolean disableEntity;

    private List<ExtraGenerator> controllerExtra = Collections.emptyList();

    /**
     * 不对外爆露
     */
    private TemplateConfig() {
        this.entity = ConstVal.TEMPLATE_ENTITY_JAVA;
        this.entityKt = ConstVal.TEMPLATE_ENTITY_KT;
        this.controller = ConstVal.TEMPLATE_CONTROLLER;
        this.mapper = ConstVal.TEMPLATE_MAPPER;
        this.xml = ConstVal.TEMPLATE_XML;
        this.service = ConstVal.TEMPLATE_SERVICE;
        this.serviceImpl = ConstVal.TEMPLATE_SERVICE_IMPL;
    }

    /**
     * 当模板赋值为空时进行日志提示打印
     *
     * @param value        模板值
     * @param templateType 模板类型
     */
    private void logger(String value, TemplateType templateType) {
        if (StringUtils.isBlank(value)) {
            LOGGER.warn("推荐使用disable(TemplateType.{})方法进行默认模板禁用.", templateType.name());
        }
    }

    /**
     * 获取实体模板路径
     *
     * @param kotlin 是否kotlin
     * @return 模板路径
     */
    public String getEntity(boolean kotlin) {
        if (!this.disableEntity) {
            if (kotlin) {
                return StringUtils.isBlank(this.entityKt) ? ConstVal.TEMPLATE_ENTITY_KT : this.entityKt;
            }
            return StringUtils.isBlank(this.entity) ? ConstVal.TEMPLATE_ENTITY_JAVA : this.entity;
        }
        return null;
    }

    public List<ExtraGenerator> getEntityExtra() {
        return this.entityExtra;
    }

    /**
     * 禁用模板
     *
     * @param templateTypes 模板类型
     * @return this
     * @since 3.3.2
     */
    public TemplateConfig disable(@NotNull TemplateType... templateTypes) {
        if (templateTypes != null && templateTypes.length > 0) {
            for (TemplateType templateType : templateTypes) {
                switch (templateType) {
                    case ENTITY:
                        this.entity = null;
                        this.entityKt = null;
                        //暂时没其他多的需求,使用一个单独的boolean变量进行支持一下.
                        this.disableEntity = true;
                        break;
                    case CONTROLLER:
                        this.controller = null;
                        break;
                    case MAPPER:
                        this.mapper = null;
                        break;
                    case XML:
                        this.xml = null;
                        break;
                    case SERVICE:
                        this.service = null;
                        break;
                    case SERVICEIMPL:
                        this.serviceImpl = null;
                        break;
                    default:
                }
            }
        }
        return this;
    }

    /**
     * 禁用全部模板
     *
     * @return this
     * @since 3.5.0
     */
    public TemplateConfig disable() {
        return disable(TemplateType.values());
    }

    public String getService() {
        return service;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public String getMapper() {
        return mapper;
    }

    public List<ExtraGenerator> getMapperExtra() {
        return this.mapperExtra;
    }

    public String getXml() {
        return xml;
    }

    public String getController() {
        return controller;
    }

    /**
     * 模板路径配置构建者
     *
     * @author nieqiurong 3.5.0
     */
    public static class Builder implements IConfigBuilder<TemplateConfig> {

        private final TemplateConfig templateConfig;

        /**
         * 默认生成一个空的
         */
        public Builder() {
            this.templateConfig = new TemplateConfig();
        }

        /**
         * 禁用所有模板
         *
         * @return this
         */
        public Builder disable() {
            this.templateConfig.disable();
            return this;
        }

        /**
         * 禁用模板
         *
         * @return this
         */
        public Builder disable(@NotNull TemplateType... templateTypes) {
            this.templateConfig.disable(templateTypes);
            return this;
        }

        /**
         * 设置实体模板路径(JAVA)
         *
         * @param entityTemplate 实体模板
         * @return this
         */
        public Builder entity(@NotNull String entityTemplate) {
            this.templateConfig.disableEntity = false;
            this.templateConfig.entity = entityTemplate;
            return this;
        }

        /**
         * 设置实体模板路径(kotlin)
         *
         * @param entityKtTemplate 实体模板
         * @return this
         */
        public Builder entityKt(@NotNull String entityKtTemplate) {
            this.templateConfig.disableEntity = false;
            this.templateConfig.entityKt = entityKtTemplate;
            return this;
        }

        /**
         * 设置实体额外模板
         *
         * @param entityExtra 实体额外模板
         * @return this
         */
        public Builder entityExtra(@NotNull List<ExtraGenerator> entityExtra) {
            this.templateConfig.entityExtra = entityExtra;
            return this;
        }

        /**
         * 设置service模板路径
         *
         * @param serviceTemplate     service接口模板路径
         * @param serviceImplTemplate service实现类模板路径
         * @return this
         */
        public Builder service(@NotNull String serviceTemplate, @NotNull String serviceImplTemplate) {
            this.templateConfig.service = serviceTemplate;
            this.templateConfig.serviceImpl = serviceImplTemplate;
            return this;
        }

        /**
         * 设置mapper模板路径
         *
         * @param mapperTemplate mapper模板路径
         * @return this
         */
        public Builder mapper(@NotNull String mapperTemplate) {
            this.templateConfig.mapper = mapperTemplate;
            return this;
        }

        /**
         * 设置mapper额外模板
         *
         * @param mapperExtra mapper额外模板
         * @return this
         */
        public Builder mapperExtra(@NotNull ExtraGenerator mapperExtra) {
            return mapperExtra(Collections.singletonList(mapperExtra));
        }

        /**
         * 设置mapper额外模板
         *
         * @param mapperExtra mapper额外模板
         * @return this
         */
        public Builder mapperExtra(@NotNull List<ExtraGenerator> mapperExtra) {
            this.templateConfig.mapperExtra = mapperExtra;
            return this;
        }

        /**
         * 设置mapperXml模板路径
         *
         * @param mapperXmlTemplate xml模板路径
         * @return this
         */
        public Builder mapperXml(@NotNull String mapperXmlTemplate) {
            this.templateConfig.xml = mapperXmlTemplate;
            return this;
        }

        /**
         * 设置控制器模板路径
         *
         * @param controllerTemplate 控制器模板路径
         * @return this
         */
        public Builder controller(@NotNull String controllerTemplate) {
            this.templateConfig.controller = controllerTemplate;
            return this;
        }

        /**
         * 构建模板配置对象
         *
         * @return 模板配置对象
         */
        @Override
        public TemplateConfig build() {
            return this.templateConfig;
        }
    }
}
