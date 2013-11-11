/*
 * Copyright 2013 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.mortar;

import android.os.Bundle;
import mortar.AbstractViewPresenter;
import mortar.HasMortarScope;
import rx.util.functions.Action0;

public class ActionBarOwner extends AbstractViewPresenter<ActionBarOwner.View> {
  private Config config;

  interface View extends HasMortarScope {
    void setUpButtonEnabled(boolean b);
    void setTitle(CharSequence title);
    void setMenu(MenuAction action);
  }

  public static class Config {
    public final boolean upButtonEnabled;
    public final CharSequence title;
    public final MenuAction action;

    public Config(boolean upButtonEnabled, CharSequence title, MenuAction action) {
      this.upButtonEnabled = upButtonEnabled;
      this.title = title;
      this.action = action;
    }

    public Config withAction(MenuAction action) {
      return new Config(upButtonEnabled, title, action);
    }
  }

  public static class MenuAction {
    public final CharSequence title;
    public final Action0 action;

    public MenuAction(CharSequence title, Action0 action) {
      this.title = title;
      this.action = action;
    }
  }

  @Override public void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);
    if (config != null) update();
  }

  public void setConfig(Config config) {
    this.config = config;
    update();
  }

  public Config getConfig() {
    return config;
  }

  private void update() {
    View view = getView();
    if (view == null) return;

    view.setUpButtonEnabled(config.upButtonEnabled);
    view.setTitle(config.title);
    view.setMenu(config.action);
  }
}