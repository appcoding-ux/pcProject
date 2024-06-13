/*
 * !! Passcode at the bottom
 */

/*
 * Predeined arrays for various stuff
 */
var terminalKeys = [
  { val: '1', dots: 3 },
  { val: '2', dots: 2 },
  { val: '3', dots: 1 },
  { val: '4', dots: 2 },
  { val: '5', dots: 3 },
  { val: '6', dots: 3 },
  { val: '7', dots: 1 },
  { val: '8', dots: 1 },
  { val: '9', dots: 1 },
  { val: '10', dots: 0 },
  { val: '11', dots: 2 },
  { val: '12', dots: 0 },
  { val: '13', dots: 1 },
  { val: '14', dots: 2 },
  { val: '15', dots: 0 },
  { val: '16', dots: 2 },
  { val: '17', dots: 0},
  { val: '18', dots: 1},
  { val: '19', dots: 1},
  { val: '20', dots: 3},
  { val: '21', dots: 3},
  { val: '22', dots: 0},
  { val: '23', dots: 0},
  { val: '24', dots: 1},
  { val: '25', dots: 1},
  { val: '26', dots: 1},
  { val: '27', dots: 1},
  { val: '28', dots: 1},
  { val: '29', dots: 1},
  { val: '30', dots: 1},
  { val: '31', dots: 1},
  { val: '32', dots: 1}];

var terminalTpl = "<header class='terminal-title'>" +
    "<h3>사용하실 좌석을 선택해주세요</h3>" +
  "</header>" +
  "<div class='seq-pad'>" +
    "<span class='seq-pad__cnr cnr--tp-lt'></span>" +
    "<span class='seq-pad__cnr cnr--tp-rt'></span>" +
    "<span class='seq-pad__cnr cnr--bt-lt'></span>" +
    "<span class='seq-pad__cnr cnr--bt-rt'></span>" +
    "<div class='seq-pad__inr'>" +
    
    "<% _.each(data, function(item,key){ %>" +
      "<% if(!item.val) { %>" +
        "<div class='pad-btn--blnk'></div>" +
      "<% } else { %>" +
        "<button class='pad-btn' key='<%= item.val %>'>" +
          "<%= item.val %>" +
          "<span class='pad-btn__bdr bdr--tp'></span>" +
          "<span class='pad-btn__bdr bdr--rt'></span>" +
          "<span class='pad-btn__bdr bdr--bt'></span>" +
          "<span class='pad-btn__bdr bdr--lt'></span>" +
          "<div class='pad-btn__sqrs'>" +
            "<% for(var i=0; i<item.dots; i++) { %>" +
              "<span class='pad-btn__sqr'></span>" +
            "<% } %>" +
          "</div>" +
        "</button>" +
      "<% } %>" +
    "<% });  %>" +
    
    "</div>" +
  "</div>";


/*
 * Set up backbone
 */
var Panel = Backbone.Model.extend({

  // defaults: {
  // 	passcode: ['C1', 'I3', '91', 'X0'],
  // 	sequence: [],
  // },

  initialize: function(opts) {
    this.opts = opts;
    this.set('sequence', []);

    this.listenTo(this, 'change', this.verify);
  },

  addKey: function(key) {
    var temp = this.get('sequence');
    temp.push(key);
    this.set('sequence', temp);
    this.trigger('change');
  },

  clearPass: function() {
    this.set('sequence', []);
  },

  testSequence: function() {
    var i = 0,
        match = true;

    for(var i=0; i< this.get('passcode').length; i++) {
      if(this.get('sequence')[i] !== this.get('passcode')[i]) {
        match = false;
        break;
      }
    }
    return match;
  },

  isIncomplete: function() {
    return (this.isEmpty() || this.isFull()) ? false : true;
  },

  isEmpty: function() {
    return (this.get('sequence').length == 0) ? true : false;
  },

  isFull: function() {
    return (this.get('sequence').length == this.get('passcode').length) ? true : false;
  },

  verify: function() {
    if (!this.isFull()) {
      return;
    } else {
      (!this.testSequence())
        ? this.trigger('mismatch')
        : this.trigger('match');
    }
  }
});

var PanelView = Backbone.View.extend({

  tagName: 'div',
  className: 'terminal',

  model: null,
  template: null,

  events: {
    'click .pad-btn': 'addKey',
    'click [pad-actn-clr]': 'resetForm'
  },

  initialize: function(opts){
    this.sequence = []; // used to store the current sequence

    this.opts = opts;

    this.template = _.template(this.opts.template);

    this.listenTo(this.model, 'change:sequence', this.toggleControls);
    this.listenTo(this.model, 'mismatch', this.setFailState);
    this.listenTo(this.model, 'match', this.setPassState);
    this.listenTo(this.model, 'destroy', this.trash);
  },

  render: function(){

    var _self = this;

    this.$el.append(this.template({'data': this.opts.data }));

    this.$btnPads    = this.$el.find('.pad-btn');
    this.$btnReset   = this.$el.find('[pad-actn-clr]');
    this.$msg        = this.$el.find('[pad-actn-msg]');
    this.$loadbar    = this.$el.find('.terminal-load-bar');

    this.$el.on('animationend webkitAnimationEnd', function(){
      if (_self.$el.hasClass('state--warp')) {
        _self.model.destroy();
      }
    });

    $('.login').append(this.$el);
  },

  addKey: function(e){
    var $temp = $(e.target);
    if ($temp.hasClass('state--active')) {
      return;
    }
    $temp.addClass('state--active');
    this.model.addKey($temp.attr('key'));
  },

  toggleControls: function(force) {
    var show = (force == true || force == false) ? force : this.model.isIncomplete();
    this.$btnReset[(show) ? 'addClass' : 'removeClass']('state--visible');
  },

  setFailState: function() {
    this.showMessage({
      success: false,
      btnClass: 'state--error',
      formMsg: 'wrong sequence',
      formMsgClass: 'state--error'
    });
  },

  setPassState: function() {
    this.showMessage({
      success: true,
      btnClass: 'state--success',
      formMsg: 'logging in',
      formMsgClass: 'state--success'
    });
  },

  showMessage: function(obj) {

    var _self = this;

    this.$btnPads.filter('.state--active')
                 .removeClass('state--active')
                 .addClass(obj.btnClass);

    this.$el.addClass('state--frozen');

    this.$msg.text(obj.formMsg).addClass('state--play ' + obj.formMsgClass);

    if (!obj.success) {
      setTimeout(function(){
        _self.resetForm();
      }, 2100);
    } else {
      setTimeout(function(){
        _self.loginForm();
      });
    }
  },

  resetForm: function() {
    this.model.clearPass();

  	this.$el.removeClass('state--frozen');
  	this.$btnPads.removeClass('state--active state--error state--success');
  	this.$msg.text('').removeClass('state--play');
  	this.toggleControls(false);
  },

  loginForm: function() {
    var _self = this;
    this.$loadbar.addClass('state--loading')
                 .on('transitionend webkitTransitionEnd', function(e){
                    _self.$el.addClass('state--warp');
                 });
  },

  trash: function() {
    toggleWelcome(true); // cheating
    this.undelegateEvents();
    this.remove();
    Backbone.View.prototype.remove.call(this);
  }
});

// terminal set up, so it can be called more than once
var initTerminal = function() {
  var login = null,
      pad = null;

  login = new Panel();
  pad = new PanelView({
    model: login,
    template: terminalTpl,
    data: terminalKeys
  });

  // render the view
  pad.render();
}



// finally launch the terminal
initTerminal();



// password solution
// 'C1', 'I3', '91', 'X0'