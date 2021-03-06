/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.ui.artifacts.smtype.filter;

import org.eclipse.hawkbit.repository.EntityFactory;
import org.eclipse.hawkbit.repository.SoftwareModuleTypeManagement;
import org.eclipse.hawkbit.ui.SpPermissionChecker;
import org.eclipse.hawkbit.ui.artifacts.event.UploadArtifactUIEvent;
import org.eclipse.hawkbit.ui.artifacts.state.ArtifactUploadState;
import org.eclipse.hawkbit.ui.common.filterlayout.AbstractFilterLayout;
import org.eclipse.hawkbit.ui.utils.UINotification;
import org.eclipse.hawkbit.ui.utils.VaadinMessageSource;
import org.vaadin.spring.events.EventBus.UIEventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

/**
 * Software module type filter buttons layout.
 */
public class SMTypeFilterLayout extends AbstractFilterLayout {

    private static final long serialVersionUID = 1L;

    private final ArtifactUploadState artifactUploadState;

    /**
     * Constructor
     * 
     * @param artifactUploadState
     *            ArtifactUploadState
     * @param i18n
     *            VaadinMessageSource
     * @param permChecker
     *            SpPermissionChecker
     * @param eventBus
     *            UIEventBus
     * @param entityFactory
     *            EntityFactory
     * @param uiNotification
     *            UINotification
     * @param softwareModuleTypeManagement
     *            SoftwareModuleTypeManagement
     * @param sMTypeFilterButtons
     *            SMTypeFilterButtons
     */
    public SMTypeFilterLayout(final ArtifactUploadState artifactUploadState, final VaadinMessageSource i18n,
            final SpPermissionChecker permChecker, final UIEventBus eventBus, final EntityFactory entityFactory,
            final UINotification uiNotification, final SoftwareModuleTypeManagement softwareModuleTypeManagement,
            final SMTypeFilterButtons sMTypeFilterButtons) {
        super(new SMTypeFilterHeader(i18n, permChecker, eventBus, artifactUploadState, entityFactory, uiNotification,
                softwareModuleTypeManagement, sMTypeFilterButtons), sMTypeFilterButtons, eventBus);
        this.artifactUploadState = artifactUploadState;
        restoreState();
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    void onEvent(final UploadArtifactUIEvent event) {
        if (event == UploadArtifactUIEvent.HIDE_FILTER_BY_TYPE) {
            setVisible(false);
        }
        if (event == UploadArtifactUIEvent.SHOW_FILTER_BY_TYPE) {
            setVisible(true);
        }
    }

    @Override
    public Boolean onLoadIsTypeFilterIsClosed() {
        return artifactUploadState.isSwTypeFilterClosed();
    }

}
